package com.study.Ex14ReadDB.controller;


import com.study.Ex14ReadDB.domain.CompanyNotice.CompanyNoticeService;
import com.study.Ex14ReadDB.domain.CompanyNotice.dto.CompanyNoticeDto;
import com.study.Ex14ReadDB.domain.CompanyNotice.dto.CompanyNoticeListDto;
import com.study.Ex14ReadDB.domain.CompanyNotice.dto.RequestCompanyNoticeDto;
import com.study.Ex14ReadDB.domain.CompanyNotice.dto.ResponseCompanyNoticeDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommunityController {

    private final CompanyNoticeService companyNoticeService;

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
            dto = companyNoticeService.findByTitle(content);
        }
        else if( category.equals("content".toUpperCase())){
            dto = companyNoticeService.findByContent(content);
        }
        else if(category.equals("member".toUpperCase())){
            dto = companyNoticeService.findByMemberId(content);
        }

        model.addAttribute("dto", dto);
        return "/community/community01";


    }

}
