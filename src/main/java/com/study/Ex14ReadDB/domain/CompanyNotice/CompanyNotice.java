package com.study.Ex14ReadDB.domain.CompanyNotice;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "company_notice")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyNotice {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long noticeIdx;

    @Column
    private String noticeTitle;

    @Column
    private String noticeContent;

    @Column
    private String noticeMemberId;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate noticeDate = LocalDate.now();


    @Builder
    public CompanyNotice(String noticeTitle, String noticeContent, String noticeMemberId) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeMemberId = noticeMemberId;
    }

    public void updateNoticeDate(LocalDate localDate){
        this.noticeDate = localDate;
    }
}
