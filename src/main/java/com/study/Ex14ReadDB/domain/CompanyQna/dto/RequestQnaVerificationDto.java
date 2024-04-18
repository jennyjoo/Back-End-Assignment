package com.study.Ex14ReadDB.domain.CompanyQna.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestQnaVerificationDto {
    private String inputPw;
    private Long qnaIdx;
}
