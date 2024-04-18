package com.study.Ex14ReadDB.domain.Member.Dto.Request;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestDuplDto {
    private String userID;
}
