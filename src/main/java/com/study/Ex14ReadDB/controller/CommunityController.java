package com.study.Ex14ReadDB.controller;


import com.study.Ex14ReadDB.domain.Community.NoticeService;
import com.study.Ex14ReadDB.domain.Community.dto.CompanyNoticeListDto;
import com.study.Ex14ReadDB.domain.Community.dto.Request.RequestCompanyNoticeDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {

    private final NoticeService noticeService;


    @GetMapping("/notice/list")
    public String noticeList(Model model){
        List<CompanyNoticeListDto> dto = noticeService.findAllList();

        model.addAttribute("dto", dto);

        return "/community/community01";
    }

    @PostMapping("/notice")
    public String notice(@ModelAttribute RequestCompanyNoticeDto requestDto,
                                           HttpSession session,
                                           Model model){

        Boolean isLogin = (Boolean) session.getAttribute("isLogin");

        if(isLogin == null) return "redirect:/";

        String category = requestDto.getCategory().toUpperCase();
        String content = requestDto.getNoticeContent();

        System.out.println("category : " + category);
        System.out.println("content : " + content);

        List<CompanyNoticeListDto> dto = null;

        if(category.equals("title".toUpperCase())){
            dto = noticeService.findByTitle(content);
        }
        else if( category.equals("content".toUpperCase())){
            dto = noticeService.findByContent(content);
        }
        else if(category.equals("member".toUpperCase())){
            dto = noticeService.findByMemberId(content);
        }

        model.addAttribute("dto", dto);
        return "/community/community01";


    }

}
