package com.study.Ex14ReadDB.domain.CompanyOne2One;

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
public class CompanyOne2One {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "one2one_idx")
    private Long one2OneIdx;

    @Column(name = "one2one_name")
    private String one2OneName;

    @Column(name = "one2one_phone")
    private String one2OnePhone;

    @Column(name = "one2one_email")
    private String one2OneEmail;

    @Column(name = "one2one_address")
    private String one2OneAddress;

    @Column(name = "one2one_title")
    private String one2OneTitle;

    @Column(name = "one2one_content")
    private String one2OneContent;

    @Column(name = "one2one_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate one2OneDate = LocalDate.now();


    @Builder
    public CompanyOne2One(String one2OneName, String one2OnePhone, String one2OneEmail, String one2OneAddress, String one2OneTitle, String one2OneContent) {
        this.one2OneName = one2OneName;
        this.one2OnePhone = one2OnePhone;
        this.one2OneEmail = one2OneEmail;
        this.one2OneAddress = one2OneAddress;
        this.one2OneTitle = one2OneTitle;
        this.one2OneContent = one2OneContent;
    }

    public void updateDate(LocalDate localDate){
        this.one2OneDate= localDate;
    }
}
