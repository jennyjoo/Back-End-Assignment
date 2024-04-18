package com.study.Ex14ReadDB.domain.Customer.Dto;


import com.study.Ex14ReadDB.domain.Customer.Entity.CompanyFaq;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class CompanyFaqDto {

    private Long faqIdx;

    private String faqTitle;

    private String faqContent;


    public CompanyFaqDto(CompanyFaq entity){
        this.faqIdx = entity.getFaqIdx();
        this.faqTitle = entity.getFaqTitle();
        this.faqContent = entity.getFaqContent();
    }
}
