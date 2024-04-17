package com.study.Ex14ReadDB.controller;


import com.study.Ex14ReadDB.domain.Member.dto.*;
import com.study.Ex14ReadDB.domain.Member.Member;
import com.study.Ex14ReadDB.domain.Member.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/loginAction")
    @ResponseBody
    public String loginAction(@ModelAttribute RequestLoginDto dto,
                              HttpSession session){

        String memberId = dto.getLoginID();
        Optional<Member> optional = memberService.findByMemberId(memberId);

        if(optional.isPresent()){
            Member member = optional.get();
            session.setAttribute("isLogin", "true");
            session.setAttribute("memberId", member.getMemberId());

            return "<script>alert('로그인 성공'); location.href='/';</script>";
        }

        return "<script>alert('로그인 실패'); history.back();</script>";
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
}
