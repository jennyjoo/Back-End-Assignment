package com.study.Ex14ReadDB.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
