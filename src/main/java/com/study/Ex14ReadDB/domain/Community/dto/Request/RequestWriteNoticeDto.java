package com.study.Ex14ReadDB.domain.Community.dto.Request;

import com.study.Ex14ReadDB.domain.Community.Entity.CompanyNotice;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestWriteNoticeDto {

    private String noticeTitle;
    private String noticeContent;

    public CompanyNotice toEntity(String writerId){
        CompanyNotice notice = CompanyNotice.builder()
                .noticeContent(this.noticeContent)
                .noticeMemberId(writerId)
                .noticeTitle(this.noticeTitle)
                .build();

        return notice;
    }


}
