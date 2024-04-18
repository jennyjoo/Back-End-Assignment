package com.study.Ex14ReadDB.domain.CompanyOne2one;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "company_one2one")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyOne2one {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "one2one_idx")
    private Long one2oneIdx;

    @Column(name = "one2one_name")
    private String one2oneName;

    @Column(name = "one2one_phone")
    private String one2onePhone;

    @Column(name = "one2one_email")
    private String one2oneEmail;

    @Column(name = "one2one_address")
    private String one2oneAddress;

    @Column(name = "one2one_title")
    private String one2oneTitle;

    @Column(name = "one2one_content")
    private String one2oneContent;

    @Column(name = "one2one_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate one2oneDate = LocalDate.now();


    @Builder
    public CompanyOne2one(String one2oneName, String one2onePhone, String one2oneEmail, String one2oneAddress, String one2oneTitle, String one2oneContent) {
        this.one2oneName = one2oneName;
        this.one2onePhone = one2onePhone;
        this.one2oneEmail = one2oneEmail;
        this.one2oneAddress = one2oneAddress;
        this.one2oneTitle = one2oneTitle;
        this.one2oneContent = one2oneContent;
    }

    public void updateDate(LocalDate localDate){
        this.one2oneDate= localDate;
    }
}
