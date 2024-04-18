package com.study.Ex14ReadDB.domain.Customer.Dto.Request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class RequestQnaVerificationDto {
    private String inputPw;
    private Long qnaIdx;
}
