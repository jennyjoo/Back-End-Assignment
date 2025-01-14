package com.study.Ex14ReadDB.domain.Customer.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.Ex14ReadDB.domain.Customer.Entity.CompanyQna;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CompanyQnaDto {

    private Long qnaIdx;
    private String qnaName;
    private String qnaPw;
    private String qnaTitle;
    private String qnaContent;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate qnaDate;


    public CompanyQnaDto(CompanyQna entity) {
        this.qnaIdx = entity.getQnaIdx();
        this.qnaName = entity.getQnaName();
        this.qnaPw = entity.getQnaPw();
        this.qnaTitle = entity.getQnaTitle();
        this.qnaContent = entity.getQnaContent();
        this.qnaDate = entity.getQnaDate();
    }
}
