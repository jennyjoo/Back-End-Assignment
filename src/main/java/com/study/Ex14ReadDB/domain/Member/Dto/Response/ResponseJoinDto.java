package com.study.Ex14ReadDB.domain.Member.Dto.Response;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseJoinDto {
    private String status;
    private String result;
}
