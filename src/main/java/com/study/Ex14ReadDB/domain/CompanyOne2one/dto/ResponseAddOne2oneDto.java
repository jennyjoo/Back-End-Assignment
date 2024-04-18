package com.study.Ex14ReadDB.domain.CompanyOne2one.dto;


import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class ResponseAddOne2oneDto {
    private String status;
    private String result;
}

