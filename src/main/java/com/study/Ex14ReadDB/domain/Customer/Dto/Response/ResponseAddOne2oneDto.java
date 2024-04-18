package com.study.Ex14ReadDB.domain.Customer.Dto.Response;


import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class ResponseAddOne2oneDto {
    private String status;
    private String result;
}

