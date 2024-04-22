package com.study.Ex14ReadDB.controller;


import com.study.Ex14ReadDB.UserSession;
import com.study.Ex14ReadDB.domain.Customer.Dto.CompanyFaqDto;
import com.study.Ex14ReadDB.domain.Customer.Dto.CompanyQnaDto;
import com.study.Ex14ReadDB.domain.Customer.Dto.Request.RequestAddOne2oneDto;
import com.study.Ex14ReadDB.domain.Customer.Dto.Request.RequestQnaVerificationDto;
import com.study.Ex14ReadDB.domain.Customer.Dto.Response.ResponseAddOne2oneDto;
import com.study.Ex14ReadDB.domain.Customer.Entity.CompanyFaq;
import com.study.Ex14ReadDB.domain.Customer.Entity.CompanyOne2one;
import com.study.Ex14ReadDB.domain.Customer.Entity.CompanyQna;
import com.study.Ex14ReadDB.domain.Customer.Service.CompanyFaqService;
import com.study.Ex14ReadDB.domain.Customer.Service.CompanyOne2oneService;
import com.study.Ex14ReadDB.domain.Customer.Service.CompanyQnaService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CompanyFaqService faqService;
    private final CompanyQnaService qnaService;
    private final CompanyOne2oneService one2oneService;

    @GetMapping("/one2one")
    public String one2one(HttpSession session){

        if(!isLogin(session)){
            return "redirect:/userNotFound";
        }

        return "/customer/customer01";
    }

    @PostMapping("/one2one/write")
    @ResponseBody
    public ResponseAddOne2oneDto one2oneWrite(@RequestBody RequestAddOne2oneDto requestDto,
                                              HttpSession session){

        System.out.println("address : " + requestDto.getAddress());
        System.out.println("name : " + requestDto.getName());

        ResponseAddOne2oneDto response = null;

        if(!isLogin(session)){
            return response = ResponseAddOne2oneDto.builder()
                    .status("fail")
                    .result("로그아웃 되었습니다")
                    .build();
        }

        CompanyOne2one one2one = one2oneService.save(requestDto);
        if(one2one != null){
            return response = ResponseAddOne2oneDto.builder()
                    .status("ok")
                    .result("등록 성공")
                    .build();
        }

        return response = ResponseAddOne2oneDto.builder()
                .status("fail")
                .result("등록 실패")
                .build();
    }



    @GetMapping("/qna/list")
    public String qnaList(@RequestParam(defaultValue = "qnaDate") String category,
                          @RequestParam(defaultValue = "") String searchKeyword,
                          HttpSession session, Model model){

        if(!isLogin(session)){
            return "redirect:/userNotFound";
        }

        List<CompanyQnaDto> dto = qnaService.findQnasBy(category, searchKeyword);
        model.addAttribute("dto", dto);
        model.addAttribute("selected", category);
        model.addAttribute("searchKeyword", searchKeyword);

        return "/customer/customer02";
    }

    @GetMapping("/qna/verifyForm")
    public String qnaVerifyForm(@RequestParam Long no,
                                HttpSession session,
                                Model model){

        if(!isLogin(session)){
            return "redirect:/userNotFound";
        }

        model.addAttribute("qnaIdx", no);
        return "/customer/customer02_3";
    }

    @PostMapping("/qna/verify")
    @ResponseBody
    public String qnaVerify(@ModelAttribute RequestQnaVerificationDto requestDto,
                            HttpSession session,
                            Model model){
        Long qnaIdx = requestDto.getQnaIdx();
        String qnaPw = requestDto.getInputPw();

        System.out.println("qnaIdx : " + qnaIdx);

        Optional<CompanyQna> optional = qnaService.findById(qnaIdx);
        if(optional.isPresent()){
            CompanyQna qna = optional.get();

            if(qna.getQnaPw().equals(qnaPw)){
                UserSession userSession = (UserSession) session.getAttribute("userSession");
                userSession.verify(qnaIdx);

                session.setAttribute("userSession", userSession);
                return "<script>location.href='/customer/qna/detail?no="+ qnaIdx +"'</script>";
            }
            return "<script>alert('비밀번호가 틀렸습니다'); history.back();</script>";
        }

        return "<script>alert('게시물이 없습니다');</script>";
    }

    @GetMapping("/qna/detail")
    public String qnaDetail(@RequestParam Long no,
                            HttpSession session,
                            Model model){

        if(!isLogin(session)){
            return "redirect:/userNotFound";
        }

        Optional<CompanyQna> optional = qnaService.findById(no);

        CompanyQnaDto dto = null;
        if(optional.isPresent()){
            dto = new CompanyQnaDto(optional.get());

            UserSession userSession = (UserSession) session.getAttribute("userSession");
            if(userSession.getIsVerified() && userSession.getVerifiedIdx().equals(dto.getQnaIdx())){

                userSession.invalidateVerification(dto.getQnaIdx());
                session.setAttribute("userSession", userSession);

                model.addAttribute("dto", dto);

                return "/customer/customer02_4";
            }
        }


        return "redirect:/customer/qna/list";

    }

    @GetMapping("/faq/list")
    public String faq(HttpSession session, Model model){

        if(!isLogin(session)){
            return "redirect:/userNotFound";
        }

        List<CompanyFaqDto> dto = faqService.findAll();
        model.addAttribute("dto", dto);

        return "/customer/customer03";

    }

    @GetMapping("/faq/detail")
    public String faqDetail(@RequestParam Long no,
                            HttpSession session,
                            Model model){

        if(!isLogin(session)){
            return "redirect:/userNotFound";
        }

        CompanyFaqDto dto = faqService.findById(no);
        model.addAttribute("dto", dto);

        return "/customer/customer03_1";
    }



    private Boolean isLogin(HttpSession session){
        UserSession userSession = (UserSession) session.getAttribute("userSession");

        if(userSession == null) return false;
        if(userSession.getIsLogin()){
            return true;
        }
        return false;
    }


}
