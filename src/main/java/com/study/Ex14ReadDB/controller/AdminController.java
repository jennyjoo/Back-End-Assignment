package com.study.Ex14ReadDB.controller;

import com.study.Ex14ReadDB.UserSession;
import com.study.Ex14ReadDB.domain.Community.Entity.CompanyNotice;
import com.study.Ex14ReadDB.domain.Community.dto.CompanyNoticeDto;
import com.study.Ex14ReadDB.domain.Community.dto.Request.RequestModifyNoticeDto;
import com.study.Ex14ReadDB.domain.Community.dto.Request.RequestWriteNoticeDto;
import com.study.Ex14ReadDB.domain.Community.dto.Response.ResponseModifyNoticeDto;
import com.study.Ex14ReadDB.domain.Member.Member;
import com.study.Ex14ReadDB.domain.Admin.AdminService;
import com.study.Ex14ReadDB.domain.Admin.MemberAdmin;
import com.study.Ex14ReadDB.domain.Member.MemberService;
import com.study.Ex14ReadDB.domain.Member.Dto.MemberDto;
import com.study.Ex14ReadDB.domain.Member.Dto.Request.RequestLoginDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

                UserSession adminSession = UserSession.makeAdminSession(admin.getMemberId());
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

        if(!isAdmin(session)){
            return "redirect:/admin";
        }

        return "redirect:/admin/memberListSearch";


    }

    @GetMapping("/memberListSearch")
    public String memberListPost(@RequestParam(name = "searchSelect", defaultValue = "all") String searchSelect,
                                 @RequestParam(name = "searchKeyword", defaultValue = "") String searchKeyword,
                                 @RequestParam(name = "orderSelect", defaultValue = "member_id_asc") String orderSelect,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "pageSize", defaultValue = "20") int pageSize,
                                 HttpSession session,
                                 HttpServletRequest request,
                                 Model model){

        if(!isAdmin(session)){
            return "redirect:/admin";
        }

//        PageRequest pageabe = page(size limit orderby("memberIdx").ascending()


        Page< Member> paging = adminService.findMembersBy(searchSelect, searchKeyword, orderSelect, page, pageSize);
        List<MemberDto> dto = paging.stream().map(MemberDto::new).collect(Collectors.toList());

        model.addAttribute("dto", dto);
        model.addAttribute("category", searchSelect);
        model.addAttribute("selected", orderSelect);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("paging", paging);
        model.addAttribute("totalCount", adminService.memberTotalCount());

        return "/admin/admin_member";
    }

    @GetMapping("/noticeList")
    public String noticeList(HttpSession session,
                             Model model){

        if(!isAdmin(session)){
            return "redirect:/admin";
        }


        return "redirect:/admin/noticeListSearch";

    }

    @GetMapping("/noticeListSearch")
    public String noticeListSearch(@RequestParam(name = "searchSelect", defaultValue = "all") String searchSelect,
                                   @RequestParam(name = "searchKeyword", defaultValue = "") String searchKeyword,
                                   @RequestParam(name = "orderSelect", defaultValue = "notice_member_id_asc") String orderSelect,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "pageSize", defaultValue = "20") int pageSize,
                                   HttpSession session,
                                   Model model){

        if(!isAdmin(session)){
            return "redirect:/admin";
        }

        System.out.println("orderSelect = " + orderSelect);


        Page<CompanyNotice> paging = adminService.findNoticesByCategory(searchSelect, searchKeyword, orderSelect, page, pageSize);
        List<CompanyNoticeDto> dto = paging.stream().map(CompanyNoticeDto::new).collect(Collectors.toList());
        model.addAttribute("dto", dto);
        model.addAttribute("category", searchSelect);
        model.addAttribute("selected", orderSelect);
        model.addAttribute("page", page);
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("paging", paging);
        model.addAttribute("totalCount", adminService.noticeTotalCount());

        return "/admin/admin_notice";

    }

    @GetMapping("/notice/write/form")
    public String noticeWrite(HttpSession session){
        if(!isAdmin(session)){
            return "redirect:/admin";
        }

        return "/admin/admin_notice_write";
    }

    @PostMapping("/notice/write/action")
    @ResponseBody
    public String noticeWriteAction(@ModelAttribute RequestWriteNoticeDto requestDto,
                                    HttpSession session){

        if(!isAdmin(session)){
            return "<script>alert('권한이 없습니다'); location.href='/admin';</script>";
        }


        UserSession userSession = (UserSession) session.getAttribute("userSession");

        String adminId = userSession.getLoginId();
        CompanyNotice notice = requestDto.toEntity(adminId);
        CompanyNotice result = adminService.addNotice(notice);

        if(notice == null){
            return "<script>alert('등록 실패'); history.back();</script>";
        }


        return "<script>alert('등록 성공'); location.href='/admin/noticeList';</script>";
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
