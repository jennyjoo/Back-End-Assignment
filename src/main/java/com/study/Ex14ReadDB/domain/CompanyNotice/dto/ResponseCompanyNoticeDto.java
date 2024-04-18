package com.study.Ex14ReadDB.domain.CompanyNotice.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseCompanyNoticeDto {
    private String status;
    private List<CompanyNoticeListDto> result;


    @Builder
    public ResponseCompanyNoticeDto(String status, List<CompanyNoticeListDto> result) {
        this.status = status;
        this.result = result;
    }
}
