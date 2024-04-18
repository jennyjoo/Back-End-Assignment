package com.study.Ex14ReadDB.controller;

import com.study.Ex14ReadDB.domain.CompanyFaq.CompanyFaqService;
import com.study.Ex14ReadDB.domain.CompanyFaq.dto.CompanyFaqDto;
import com.study.Ex14ReadDB.domain.CompanyNotice.CompanyNoticeService;
import com.study.Ex14ReadDB.domain.CompanyNotice.dto.CompanyNoticeDto;
import com.study.Ex14ReadDB.domain.CompanyNotice.dto.CompanyNoticeListDto;
import com.study.Ex14ReadDB.domain.CompanyQna.CompanyQna;
import com.study.Ex14ReadDB.domain.CompanyQna.CompanyQnaService;
import com.study.Ex14ReadDB.domain.CompanyQna.dto.CompanyQnaDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final CompanyNoticeService companyNoticeService;
    private final CompanyFaqService companyFaqService;
    private final CompanyQnaService companyQnaService;


    @GetMapping("/")
    public String main(HttpSession session){

        session.setAttribute("isLogin", session.getAttribute("isLogin"));
        return "index"; //index.html로 응답
    }

    @GetMapping("/member/login")
    public String login(){
        return "/member/login";
    }


    @GetMapping("/member/join")
    public String join(){
        return "/member/join2"; //join2.html 응답
    }
    @GetMapping("/idFind")
    public String idFind(){
        return "/member/idFind"; //idFind.html 응답
    }
    @GetMapping("/passwordFind")
    public String passwordFind(){
        return "/member/passwordFind"; //idFind.html 응답
    }
    @GetMapping("/community/community01")
    public String community01(Model model){

        List<CompanyNoticeListDto> dto = companyNoticeService.findAllList();
        model.addAttribute("dto", dto);

        return "/community/community01";
    }
    @GetMapping("/community/community01_1")
    public String communityDetail(@RequestParam int no, Model model){
        Long idx = Long.valueOf(no);

        CompanyNoticeDto dto = companyNoticeService.findByIdx(idx);
        model.addAttribute("dto", dto);

        return "/community/community01_1";
    }


    @GetMapping("/community/customer/oneToone")
    public String oneToone(){
        return "/customer/customer01";
    }
    @GetMapping("/community/customer/customer01")
    @ResponseBody
    public String customerOne2one(HttpSession session){

        Boolean isLogin = (Boolean) session.getAttribute("isLogin");
        if(isLogin != null && isLogin){
            return "<script>location.href='/community/customer/oneToone'</script>";
        }
        else{
            return "<script>alert('로그인 해주세요'); location.href='/member/login'</script>";
        }


    }

    @GetMapping("/community/customer/customer2")
    public String customerQnA2(Model model){

        List<CompanyQnaDto> dto = companyQnaService.findAll();
        model.addAttribute("dto", dto);
        return "/customer/customer02";
    }

    @GetMapping("/community/customer/customer02_4")
    public String customer02_4(@RequestParam Long qnaIdx,
                               Model model,
                               HttpSession session){
        Optional<CompanyQna> optional = companyQnaService.findById(qnaIdx);

        CompanyQnaDto dto = null;
        if(optional.isPresent()){
            dto = new CompanyQnaDto(optional.get());
        }

        model.addAttribute("dto", dto);

        return "/customer/customer02_4";
    }

    @GetMapping("/community/customer/customer2_2")
    public String customerQnA2_2(@RequestParam Long no,
                                 Model model){

        model.addAttribute("qnaIdx", no);
        return "/customer/customer02_3";
    }

    @GetMapping("/community/customer/customer02")
    @ResponseBody
    public String customerQnA(HttpSession session){

        Boolean isLogin = (Boolean) session.getAttribute("isLogin");
        if(isLogin != null && isLogin){
            return "<script>location.href='/community/customer/customer2'</script>";
        }
        else{
            return "<script>alert('로그인 해주세요'); location.href='/member/login'</script>";
        }
    }


    @GetMapping("/community/customer/customer3")
    public String customerFaQ2(Model model){
        List<CompanyFaqDto> dto = companyFaqService.findAll();
        model.addAttribute("dto", dto);

        return "/customer/customer03";
    }

    @GetMapping("/community/customer/customer03")
    @ResponseBody
    public String customerFaQ(HttpSession session){

        Boolean isLogin = (Boolean) session.getAttribute("isLogin");
        if(isLogin != null && isLogin){
            return "<script>location.href='/community/customer/customer3'</script>";
        }
        else{
            return "<script>alert('로그인 해주세요'); location.href='/member/login'</script>";
        }
    }

    @GetMapping("/community/customer/customer03/detail")
    public String customerFaQDetail(@RequestParam Long faqIdx, Model model){

        CompanyFaqDto dto = companyFaqService.findById(faqIdx);
        model.addAttribute("dto", dto);
        return "/customer/customer03_1";
    }



}
