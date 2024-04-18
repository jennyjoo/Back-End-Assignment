package com.study.Ex14ReadDB.domain.CompanyNotice.dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestCompanyNoticeDto {
    private String category;
    private String noticeContent;
}
