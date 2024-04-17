package com.study.Ex14ReadDB.domain.CompanyNews;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "company_news")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long newsIdx;

    @Column
    private String newsTitle;

    @Column
    private String newsContent;

    @Column
    private String newsMemberId;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate newsDate = LocalDate.now();


    @Builder
    public CompanyNews(String newsTitle, String newsContent, String newsMemberId) {
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
        this.newsMemberId = newsMemberId;
    }

    public void updateNewsDate(LocalDate localDate){
        this.newsDate = localDate;
    }
}
