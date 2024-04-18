package com.study.Ex14ReadDB.domain.CompanyQnaReply;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "company_qna_reply")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyQnaReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long qnaReplyIdx;

    @Column
    private String qnaReplyContent;

    @Column
    private String qnaReplyName; //답글 단 사람

    @Column
    private LocalDate qnaReplyDate = LocalDate.now();

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Long qnaReplyQnaIdx; //문의 글의 인덱스

    public CompanyQnaReply(String qnaReplyContent, String qnaReplyName, Long qnaReplyQnaIdx) {
        this.qnaReplyContent = qnaReplyContent;
        this.qnaReplyName = qnaReplyName;
        this.qnaReplyQnaIdx = qnaReplyQnaIdx;
    }
}
