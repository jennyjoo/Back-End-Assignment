package com.study.Ex14ReadDB.domain.CompanyNotice.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.Ex14ReadDB.domain.CompanyNotice.CompanyNotice;
import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyNoticeDto {

    private Long noticeIdx;
    private String noticeTitle;
    private String noticeContent;
    private String noticeMemberId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate noticeDate = LocalDate.now();


    public CompanyNoticeDto(CompanyNotice entity){
        this.noticeIdx = entity.getNoticeIdx();
        this.noticeTitle = entity.getNoticeTitle();
        this.noticeContent = entity.getNoticeContent();
        this.noticeMemberId = entity.getNoticeMemberId();
    }


    @Builder
    public CompanyNoticeDto(Long noticeIdx, String noticeTitle, String noticeContent, String noticeMemberId, LocalDate noticeDate) {
        this.noticeIdx = noticeIdx;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeMemberId = noticeMemberId;
        this.noticeDate = noticeDate;
    }
}
