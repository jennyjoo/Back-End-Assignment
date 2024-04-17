package com.study.Ex14ReadDB.domain.CompanyOne2OneReply;


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
public class CompanyOne2OneReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "one2one_reply_idx")
    private Long one2OneReplyIdx;

    @Column(name = "one2one_reply_content")
    private String one2OneReplyContent;

    @Column(name = "one2one_reply_name")
    private String one2OneReplyName; //답글 단 사람

    @Column(name = "one2one_reply_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate one2OneReplyDate = LocalDate.now();

    @Column(name = "one2one_reply_one2one_idx")
    private Long one2OneReplyOne2OneIdx;


    @Builder
    public CompanyOne2OneReply(String one2OneReplyContent, String one2OneReplyName, Long one2OneReplyOne2OneIdx) {
        this.one2OneReplyContent = one2OneReplyContent;
        this.one2OneReplyName = one2OneReplyName;
        this.one2OneReplyOne2OneIdx = one2OneReplyOne2OneIdx;
    }

    public void updateReplyDate(LocalDate localDate){
        this.one2OneReplyDate = localDate;
    }
}
