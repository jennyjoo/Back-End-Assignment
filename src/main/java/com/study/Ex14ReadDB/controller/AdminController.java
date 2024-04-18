package com.study.Ex14ReadDB.controller;

import com.study.Ex14ReadDB.domain.Member.AdminService;
import com.study.Ex14ReadDB.domain.Member.MemberAdmin;
import com.study.Ex14ReadDB.domain.Member.MemberService;
import com.study.Ex14ReadDB.domain.Member.dto.MemberDto;
import com.study.Ex14ReadDB.domain.Member.dto.RequestJoinDto;
import com.study.Ex14ReadDB.domain.Member.dto.RequestLoginDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final MemberService memberService;

    @GetMapping("/admin")
    public String adminLogin(){

        return "/admin/admin_login";
    }

    @PostMapping("/adminLoginAction")
    @ResponseBody
    public String adminLoginAction(@ModelAttribute RequestLoginDto requestDto,
                                   HttpSession session){

        Optional<MemberAdmin> optional = adminService.findById(requestDto.getLoginID());

        System.out.println("ID : " + requestDto.getLoginID());

        if(optional.isPresent()){
            MemberAdmin admin = optional.get();
            if(admin.getMemberPw().equals(requestDto.getLoginPW())){
                session.setAttribute("ROLE", "ADMIN");
                session.setAttribute("isLogin", true);
                return "<script>alert('로그인 성공'); location.href='/admin/memberList';</script>";
            }
            return "<script>alert('비밀번호가 틀렸습니다'); history.back();</script>";
        }
        return "<script>alert('아이디가 틀렸습니다'); history.back();</script>";
    }

    @GetMapping("/admin/memberList")
    public String memberList(@RequestParam String query,
                             HttpSession session,
                             Model model){

        Boolean isLogin = (Boolean) session.getAttribute("isLogin" );
        String role =  (String) session.getAttribute("ROLE");

        System.out.println("isLogin : " + isLogin);
        System.out.println("role : " + role);
        if(isLogin != null && isLogin && role.toUpperCase().equals("ADMIN")){

            List<MemberDto> dto = memberService.findAll();
            model.addAttribute("dto", dto);

            return "/admin/admin_member";
        }

        return "redirect:/admin";
    }


    @PostMapping("/admin/filterMember")


}
