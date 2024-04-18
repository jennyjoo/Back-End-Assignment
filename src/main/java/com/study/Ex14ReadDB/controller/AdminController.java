package com.study.Ex14ReadDB.controller;

import com.study.Ex14ReadDB.UserSession;
import com.study.Ex14ReadDB.domain.Community.Entity.CompanyNotice;
import com.study.Ex14ReadDB.domain.Community.dto.CompanyNoticeDto;
import com.study.Ex14ReadDB.domain.Community.dto.Request.RequestModifyNoticeDto;
import com.study.Ex14ReadDB.domain.Community.dto.Response.ResponseModifyNoticeDto;
import com.study.Ex14ReadDB.domain.MemberDomain.AdminService;
import com.study.Ex14ReadDB.domain.MemberDomain.MemberAdmin;
import com.study.Ex14ReadDB.domain.Member.MemberService;
import com.study.Ex14ReadDB.domain.Member.Dto.MemberDto;
import com.study.Ex14ReadDB.domain.Member.Dto.Request.RequestLoginDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final MemberService memberService;

    @GetMapping("")
    public String adminLogin(){

        return "/admin/admin_login";
    }

    @PostMapping("/loginAction")
    @ResponseBody
    public String adminLoginAction(@ModelAttribute RequestLoginDto requestDto,
                                   HttpSession session){

        Optional<MemberAdmin> optional = adminService.findById(requestDto.getLoginID());


        if(optional.isPresent()){
            MemberAdmin admin = optional.get();
            if(admin.getMemberPw().equals(requestDto.getLoginPW())){

                UserSession adminSession = UserSession.makeAdminSession();
                adminSession.login();
                session.setAttribute("userSession", adminSession);

                return "<script>alert('로그인 성공'); location.href='/admin/memberList';</script>";
            }
            return "<script>alert('비밀번호가 틀렸습니다'); history.back();</script>";
        }
        return "<script>alert('아이디가 틀렸습니다'); history.back();</script>";
    }

    @GetMapping("/memberList")
    public String memberList(HttpSession session,
                             Model model){


        if(isAdmin(session)){

            List<MemberDto> dto = memberService.findAll();
            model.addAttribute("dto", dto);

            return "/admin/admin_member";
        }

        return "redirect:/admin";
    }

    @GetMapping("/memberListSearch")
    public String memberListPost(@RequestParam(name = "searchSelect") String searchSelect,
                                 @RequestParam(name = "searchKeyword", defaultValue = "") String searchKeyword,
                                 @RequestParam(name = "orderSelect", defaultValue = "all") String orderSelect,
                                 HttpSession session,
                                 Model model){

        if(!isAdmin(session)){
            return "redirect:/admin";
        }

        List<MemberDto> dto = adminService.findMembersByCategory(searchSelect,searchKeyword, orderSelect);
        model.addAttribute("dto", dto);
        model.addAttribute("category", searchSelect);
        model.addAttribute("selected", orderSelect);
        model.addAttribute("searchKeyword", searchKeyword);

        return "/admin/admin_member";
    }

    @GetMapping("/noticeList")
    public String noticeList(HttpSession session,
                             Model model){

        if(!isAdmin(session)){
            return "redirect:/admin";
        }

        List<CompanyNoticeDto> dto = adminService.findAllNotice();
        model.addAttribute("dto", dto);

        return "/admin/admin_notice";

    }

    @GetMapping("/noticeListSearch")
    public String noticeListSearch(@RequestParam(name = "searchSelect") String searchSelect,
                                   @RequestParam(name = "searchKeyword", defaultValue = "") String searchKeyword,
                                   @RequestParam(name = "orderSelect", defaultValue = "all") String orderSelect,
                                   HttpSession session,
                                   Model model){

        if(!isAdmin(session)){
            return "redirect:/admin";
        }

        List<CompanyNoticeDto> dto = adminService.findNoticesByCategory(searchSelect, searchKeyword, orderSelect);
        model.addAttribute("dto", dto);
        model.addAttribute("category", searchSelect);
        model.addAttribute("selected", orderSelect);
        model.addAttribute("searchKeyword", searchKeyword);

        return "/admin/admin_notice";

    }

    @GetMapping("/notice/write")
    public String noticeWrite(HttpSession session){
        if(!isAdmin(session)){
            return "redirect:/admin";
        }

        return "/admin/admin_notice_write";
    }


    @GetMapping("/notice/view")
    public String noticeDetail(@RequestParam Long no,
                               HttpSession session,
                               Model model){
        if(!isAdmin(session)){
            return "redirect:/admin";
        }

        Optional<CompanyNotice> optional = adminService.findNoticeById(no);

        if(optional.isPresent()){
            model.addAttribute("dto", new CompanyNoticeDto(optional.get()));
        }
        else{
            CompanyNoticeDto dto = new CompanyNoticeDto(null,
                    "삭제된 공지사항입니다",
                    "삭제된 공지사항입니다",
                    "", null);

            model.addAttribute("dto", dto);
        }


        return "admin/admin_notice_view";
    }


    @PostMapping("/notice/modify")
    @ResponseBody
    public String noticeModify(@ModelAttribute RequestModifyNoticeDto requestDto,
                                                HttpSession session){

        ResponseModifyNoticeDto response = null;
        if(!isAdmin(session)){
            return "<script>alert('로그인 필요함'); location.href='/admin';</script>";
        }

        Optional<CompanyNotice> optional = adminService.findNoticeById(requestDto.getNoticeIdx());

        if(optional.isPresent()){
            CompanyNotice notice = optional.get();
            notice.updateNoticeContent(requestDto.getNoticeContent());

            adminService.updateNotice(notice);


            response = ResponseModifyNoticeDto.builder()
                    .status("ok")
                    .result("수정 성공")
                    .build();

            return "<script>alert('수정 성공'); location.href='/admin/noticeList';</script>";
        }


        return "<script>alert('수정 실패'); history.back();</script>";
    }


    @GetMapping("/notice/delete")
    @ResponseBody
    public String noticeDelete(@RequestParam Long no){
        adminService.delete(no);

        if(!adminService.existsbyNoticeIdx(no)){
            return "<script>alert('삭제성공'); location.href='/admin/noticeList';</script>";
        }

        return "<script>alert('삭제실패'); history.back(); </script>";

    }

    private Boolean isAdmin(HttpSession session){
        UserSession userSession = (UserSession) session.getAttribute("userSession");
        if(userSession == null) return false;
        if(userSession.getIsLogin() && userSession.getROLE().toUpperCase().equals("ADMIN")){
            return true;
        }

        return false;
    }

}
