package com.study.Ex14ReadDB.controller;


import com.study.Ex14ReadDB.domain.Member.dto.*;
import com.study.Ex14ReadDB.domain.Member.Member;
import com.study.Ex14ReadDB.domain.Member.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/member/logout")
    public String logout(Model model, HttpSession session){

        session.invalidate();
        return "redirect:/";

    }
    @PostMapping("/loginAction")
    @ResponseBody
    public String loginAction(@ModelAttribute RequestLoginDto dto,
                              HttpSession session){

        Optional<Member> hasID = memberService.findByMemberId(dto.getLoginID());
        if(hasID.isPresent()){
            Member member = hasID.get();

            if(member.getMemberPw().equals(dto.getLoginPW())){
                session.setAttribute("isLogin", true);
                return "<script>alert('로그인 성공'); location.href='/';</script>";
            }

            return "<script>alert('비밀번호가 틀렸습니다'); history.back();</script>";
        }

        return "<script>alert('그런 아이디가 없습니다'); history.back();</script>";

    }

    @PostMapping("/fetchDupl")
    @ResponseBody
    public ResponseDuplDto fetchDupl(@RequestBody RequestDuplDto requestDto){

        Boolean isMember = memberService.existsByMemberId(requestDto.getUserID());

        ResponseDuplDto response;

        if(!isMember){
            response = ResponseDuplDto.builder()
                    .status("ok")
                    .result("사용 가능한 아이디")
                    .build();
            return response;
        }
        response = ResponseDuplDto.builder()
                .status("fail")
                .result("이미 사용중인 아이디")
                .build();

        return response;
    }

    @PostMapping("/member/join")
    @ResponseBody
    public String join(@ModelAttribute RequestJoinDto dto){

        Boolean added = memberService.addMember(dto.toEntity());
        if(added){
            return "<script>alert('가입 성공'); location.href='/';</script>";
        }
        return "<script>alert('가입 실패'); history.back();</script>";
    }

    @PostMapping("/idFind")
    @ResponseBody
    public ResponseIdFindDto idFind(@RequestBody RequestIdFindDto requestDto){
        String userName = requestDto.getUserName();
        String userEmail = requestDto.getUserEmail();

        System.out.println("크롱 : " + requestDto.getUserName());
        Optional<Member> optional = memberService.findMemberByUserNameAndEmail(userName, userEmail);

        ResponseIdFindDto response;
        if(optional.isPresent()){
            Member member = optional.get();
            response = ResponseIdFindDto.builder()
                    .status("ok")
                    .userID(member.getMemberId())
                    .build();

            return response;
        }

        response = ResponseIdFindDto.builder()
                .status("fail")
                .build();

        return response;
    }

    @PostMapping("/pwFind")
    @ResponseBody
    public ResponsePwFindDto pwFind(@RequestBody RequestPwFindDto requestDto){

        String userName = requestDto.getUserName();
        String userEmail = requestDto.getUserEmail();
        String userID = requestDto.getUserID();

        Member member = memberService.findMemberPw(userName, userEmail, userID);


        ResponsePwFindDto response;
        if(member != null){
            response = ResponsePwFindDto.builder()
                    .status("ok")
                    .userPW(member.getMemberPw())
                    .build();

            return response;
        }

        response = ResponsePwFindDto.builder()
                .status("fail")
                .result("그런 멤버 없음")
                .build();

        return response;
    }

}
