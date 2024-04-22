package com.study.Ex14ReadDB.domain.Community.dto.Response;


import com.study.Ex14ReadDB.domain.Community.dto.CompanyNoticeDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseCompanyNoticeDto {
    private String status;
    private List<CompanyNoticeDto> result;


    @Builder
    public ResponseCompanyNoticeDto(String status, List<CompanyNoticeDto> result) {
        this.status = status;
        this.result = result;
    }
}
