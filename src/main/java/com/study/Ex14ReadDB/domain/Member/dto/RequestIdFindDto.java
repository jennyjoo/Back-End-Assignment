package com.study.Ex14ReadDB.domain.Member.dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestIdFindDto {
    private String userName;
    private String userEmail;
}
