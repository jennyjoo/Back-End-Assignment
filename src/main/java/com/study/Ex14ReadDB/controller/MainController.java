package com.study.Ex14ReadDB.controller;

import com.study.Ex14ReadDB.UserSession;
import com.study.Ex14ReadDB.domain.Customer.Service.CompanyFaqService;
import com.study.Ex14ReadDB.domain.Customer.Dto.CompanyFaqDto;
import com.study.Ex14ReadDB.domain.Community.NoticeService;
import com.study.Ex14ReadDB.domain.Community.dto.CompanyNoticeDto;
import com.study.Ex14ReadDB.domain.Community.dto.CompanyNoticeListDto;
import com.study.Ex14ReadDB.domain.Customer.Entity.CompanyQna;
import com.study.Ex14ReadDB.domain.Customer.Service.CompanyQnaService;
import com.study.Ex14ReadDB.domain.Customer.Dto.CompanyQnaDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {


    @GetMapping("/")
    public String main(){
        return "index";
    }

    @GetMapping("/userNotFound")
    @ResponseBody
    public String userNotFound(){
        return "<script>alert('로그인을 해주세요'); location.href='/member/login'</script>";
    }


}
