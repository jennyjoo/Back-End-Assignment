package com.study.Ex14ReadDB.controller;


import com.study.Ex14ReadDB.domain.Community.NoticeService;
import com.study.Ex14ReadDB.domain.Community.dto.CompanyNoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {

    private final NoticeService noticeService;


    @GetMapping("/notice/list")
    public String noticeList(Model model){


        return "redirect:/community/notice";
    }

    @GetMapping("/notice")
    public String notice(@RequestParam(name = "category", defaultValue = "noticeDate") String category,
                         @RequestParam(name = "searchKeyword", defaultValue = "") String searchKeyword,
                         Model model){


        List<CompanyNoticeDto> dto = noticeService.findAllNoticesBy(category, searchKeyword);

        model.addAttribute("dto", dto);
        model.addAttribute("category", category);
        model.addAttribute("searchKeyword", searchKeyword);


        return "/community/community01";
    }

    @GetMapping("/notice/detail")
    public String noticDetail(@RequestParam(name = "no") Long no,
                              Model model){

        CompanyNoticeDto dto = noticeService.findByIdx(no);
        model.addAttribute("dto", dto);

        return "/community/community01_1";

    }

}
