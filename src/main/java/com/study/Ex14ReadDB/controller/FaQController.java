package com.study.Ex14ReadDB.controller;


import com.study.Ex14ReadDB.domain.CompanyFaq.CompanyFaqService;
import com.study.Ex14ReadDB.domain.CompanyOne2one.CompanyOne2one;
import com.study.Ex14ReadDB.domain.CompanyOne2one.CompanyOne2oneService;
import com.study.Ex14ReadDB.domain.CompanyOne2one.dto.RequestAddOne2oneDto;
import com.study.Ex14ReadDB.domain.CompanyOne2one.dto.ResponseAddOne2oneDto;
import com.study.Ex14ReadDB.domain.CompanyQna.CompanyQna;
import com.study.Ex14ReadDB.domain.CompanyQna.CompanyQnaService;
import com.study.Ex14ReadDB.domain.CompanyQna.dto.RequestQnaVerificationDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class FaQController {

    private final CompanyFaqService companyFaqService;
    private final CompanyOne2oneService companyOne2oneService;
    private final CompanyQnaService qnaService;

    @PostMapping("/addFaQ")
    public String addQna(){
        return "";
    }

    @PostMapping("/addOne2one")
    public ResponseAddOne2oneDto addOne2one(@RequestBody RequestAddOne2oneDto requestDto){
        System.out.println("address : " + requestDto.getAddress());
        CompanyOne2one one2one = companyOne2oneService.save(requestDto);

        ResponseAddOne2oneDto response;
        if(one2one != null){
            response = ResponseAddOne2oneDto.builder()
                    .status("ok")
                    .result("등록 성공")
                    .build();

            return response;
        }

        response = ResponseAddOne2oneDto.builder()
                .status("fail")
                .result("등록 실패")
                .build();

        return response;
    }

    @PostMapping("/qnaVerification")
    @ResponseBody
    public String qnaVerify(@ModelAttribute RequestQnaVerificationDto requestDto,
                            HttpSession session){

        Boolean isLogin = (Boolean) session.getAttribute("isLogin");
        if(isLogin == null || !isLogin){
            return "<script> alert('로그인 해주세요'); location.href='/member/login';</script>";
        }

        Optional<CompanyQna> optional = qnaService.findById(requestDto.getQnaIdx());

        if(optional.isPresent()){
            CompanyQna qna = optional.get();
            String pw = qna.getQnaPw();
            String inputPw = requestDto.getInputPw();

            if(pw.equals(inputPw)){
                session.setAttribute("isQnaVerified", true);
                return "<script>alert('인증 성공'); location.href='/'; </script>";
            }

            return "<script>alert('비밀번호 틀림'); history.back(); </script>";
        }

        return "<script> alert('에러'); history.back(); </script>";
    }

}
