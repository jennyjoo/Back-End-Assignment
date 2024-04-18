package com.study.Ex14ReadDB.domain.Member.Dto.Response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseDuplDto {
    private String status;
    private String result;
}
