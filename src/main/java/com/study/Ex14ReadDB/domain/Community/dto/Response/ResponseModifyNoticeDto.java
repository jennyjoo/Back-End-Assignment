package com.study.Ex14ReadDB.domain.Community.dto.Response;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseModifyNoticeDto {
    private String status;
    private String result;


    @Builder
    public ResponseModifyNoticeDto(String status, String result) {
        this.status = status;
        this.result = result;
    }
}
