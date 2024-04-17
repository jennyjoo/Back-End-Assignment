package com.study.Ex14ReadDB.controller;


import com.study.Ex14ReadDB.domain.Member.dto.RequestDuplDto;
import com.study.Ex14ReadDB.domain.Member.dto.RequestJoinDto;
import com.study.Ex14ReadDB.domain.Member.dto.RequestLoginDto;
import com.study.Ex14ReadDB.domain.Member.Member;
import com.study.Ex14ReadDB.domain.Member.MemberService;
import com.study.Ex14ReadDB.domain.Member.dto.ResponseDuplDto;
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

//        String userName = dto.getUserName();
//        String userId = dto.getUserID();
//        String userPW = dto.getUserPW();
//        String userEmail = dto.getEmailID() + "@" + dto.getEmailDomain();
//        Integer userReceive = dto.getReceive();
//        Integer passwordCheckQuestion = dto.getPasswordCheckQuestion();
//        String passwordCheckAnswer = dto.getPasswordCheckAnswer();
//        String userGender = dto.getGender();




        Boolean added = memberService.addMember(dto.toEntity());
        if(added){
            return "<script>alert('가입 성공'); location.href='/';</script>";
        }
        return "<script>alert('가입 실패'); history.back();</script>";
    }

}
