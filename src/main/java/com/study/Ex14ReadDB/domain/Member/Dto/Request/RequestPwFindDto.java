package com.study.Ex14ReadDB.domain.Member.Dto.Request;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestPwFindDto {
    private String userName;
    private String userID;
    private String userEmail;
}
