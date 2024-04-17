package com.study.Ex14ReadDB.domain.CompanyJob;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "company_job")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyJob {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long jobIdx;

    @Column
    private String jobTitle;

    @Column
    private String jobContent;

    @Column
    private String jobMemberId;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate jobDate = LocalDate.now();


    @Builder
    public CompanyJob(String jobTitle, String jobContent, String jobMemberId) {
        this.jobTitle = jobTitle;
        this.jobContent = jobContent;
        this.jobMemberId = jobMemberId;
    }

    public void updateJobDate(LocalDate localDate){
        this.jobDate = localDate;
    }
}
