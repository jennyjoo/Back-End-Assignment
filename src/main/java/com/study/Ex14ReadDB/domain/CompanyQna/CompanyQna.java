package com.study.Ex14ReadDB.domain.CompanyQna;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "company_qna")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyQna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long qnaIdx;

    @Column
    private String qnaName;

    @Column
    private String qnaPw;

    @Column
    private String qnaTitle;

    @Column
    private String qnaContent;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate qnaDate;

}
