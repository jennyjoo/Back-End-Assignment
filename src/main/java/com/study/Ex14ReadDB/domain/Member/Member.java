package com.study.Ex14ReadDB.domain.Member;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "company_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long memberIdx;

    @Column
    private String memberId;

    @Column
    private String memberPw;

    @Column
    private String memberName;

    @Column
    private String memberEmail;

    @Column
    private Integer memberEmailReceive;

    @Column
    private Integer memberPwQuestion;

    @Column
    private String memberPwAnswer;

    @Column
    private String memberGender;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate memberBirthDate;

    @Column
    private LocalDate MemberJoinDate = LocalDate.now();


    @Builder
    public Member(String memberId, String memberPw, String memberName, String memberEmail, Integer memberEmailReceive, Integer memberPwQuestion, String memberPwAnswer, String memberGender, LocalDate memberBirthDate) {
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberEmailReceive = memberEmailReceive;
        this.memberPwQuestion = memberPwQuestion;
        this.memberPwAnswer = memberPwAnswer;
        this.memberGender = memberGender;
        this.memberBirthDate = memberBirthDate;
    }

    public void updateJoinDate(LocalDate localDate){
        this.MemberJoinDate = localDate;
    }

}
