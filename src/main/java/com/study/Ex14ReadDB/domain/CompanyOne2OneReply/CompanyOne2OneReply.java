package com.study.Ex14ReadDB.domain.CompanyOne2oneReply;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "company_one2one_reply")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyOne2oneReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "one2one_reply_idx")
    private Long one2oneReplyIdx;

    @Column(name = "one2one_reply_content")
    private String one2oneReplyContent;

    @Column(name = "one2one_reply_name")
    private String one2oneReplyName; //답글 단 사람

    @Column(name = "one2one_reply_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate one2oneReplyDate = LocalDate.now();

    @Column(name = "one2one_reply_one2one_idx")
    private Long one2oneReplyOne2oneIdx;


    @Builder
    public CompanyOne2oneReply(String one2oneReplyContent, String one2oneReplyName, Long one2oneReplyOne2OneIdx) {
        this.one2oneReplyContent = one2oneReplyContent;
        this.one2oneReplyName = one2oneReplyName;
        this.one2oneReplyOne2oneIdx = one2oneReplyOne2oneIdx;
    }

    public void updateReplyDate(LocalDate localDate){
        this.one2oneReplyDate = localDate;
    }
}
