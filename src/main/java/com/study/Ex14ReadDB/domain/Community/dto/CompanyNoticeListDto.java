package com.study.Ex14ReadDB.domain.Community.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.Ex14ReadDB.domain.Community.Entity.CompanyNotice;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyNoticeListDto {

    private Long noticeIdx;
    private String noticeTitle;
    private String noticeContent;
    private String noticeMemberId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate noticeDate = LocalDate.now();





    public CompanyNoticeListDto(CompanyNotice entity){
        this.noticeIdx = entity.getNoticeIdx();
        this.noticeTitle = entity.getNoticeTitle();
        this.noticeDate = entity.getNoticeDate();
        this.noticeContent = entity.getNoticeContent();
        this.noticeMemberId = entity.getNoticeMemberId();
    }

    @Builder
    public CompanyNoticeListDto(Long noticeIdx, String noticeTitle, String noticeContent, String noticeMemberId, LocalDate noticeDate) {
        this.noticeIdx = noticeIdx;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeMemberId = noticeMemberId;
        this.noticeDate = noticeDate;
    }
}