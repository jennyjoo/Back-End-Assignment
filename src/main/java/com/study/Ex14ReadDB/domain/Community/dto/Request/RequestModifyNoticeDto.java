package com.study.Ex14ReadDB.domain.Community.dto.Request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestModifyNoticeDto {
    private Long noticeIdx;
    private String noticeContent;
}
