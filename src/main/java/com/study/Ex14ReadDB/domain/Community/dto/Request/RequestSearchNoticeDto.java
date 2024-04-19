package com.study.Ex14ReadDB.domain.Community.dto.Request;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestSearchNoticeDto {

    private String category;
    private String searchKeyword;

}
