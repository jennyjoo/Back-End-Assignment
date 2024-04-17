package com.study.Ex14ReadDB.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(){
        return "index"; //index.html로 응답
    }

    @GetMapping("/member/login")
    public String login(){
        return "/member/login";
    }

//    @PostMapping("/member/loginAction")
//    @ResponseBody
//    public String loginAction(@ModelAttribute ){
//
//    }

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
    public String community01(){
        return "/community/community01";
    }
    @GetMapping("community01_1")
    public String community01_1(@RequestParam Long no){
        return "/community/community01_1";
    }
}
