package com.study.Ex14ReadDB.domain.CompanyFaq;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "company_faq")
public class CompanyFaq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long faqIdx;

    @Column
    private String faqTitle;

    @Column
    private String faqContent;


    @Builder
    public CompanyFaq(Long faqIdx, String faqTitle, String faqContent) {
        this.faqIdx = faqIdx;
        this.faqTitle = faqTitle;
        this.faqContent = faqContent;
    }


    public void updateTitle(String faqTitle){
        this.faqTitle = faqTitle;
    }

    public void updateContent(String faqContent){
        this.faqContent = faqContent;
    }


}
