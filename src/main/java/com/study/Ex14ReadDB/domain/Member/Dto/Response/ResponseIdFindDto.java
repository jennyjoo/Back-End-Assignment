package com.study.Ex14ReadDB.domain.Member.Dto.Response;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseIdFindDto {
    private String status;
    private String userID;
}
