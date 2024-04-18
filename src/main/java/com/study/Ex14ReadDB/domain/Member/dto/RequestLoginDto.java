package com.study.Ex14ReadDB.domain.Member.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RequestLoginDto {
    private String loginID;
    private String loginPW;

}
