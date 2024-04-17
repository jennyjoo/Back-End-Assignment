package com.study.Ex14ReadDB.domain.Member.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ResponsePwFindDto {
    private String status;
    private String userPW;
    private String result;
}
