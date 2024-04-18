package com.study.Ex14ReadDB.domain.Member.Dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.Ex14ReadDB.domain.Member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {

    private Long memberIdx;

    private String memberId;

    private String memberPw;

    private String memberName;

    private String memberEmail;

    private Integer memberEmailReceive;

    private Integer memberPwQuestion;

    private String memberPwAnswer;

    private String memberGender;

    private LocalDate memberBirthDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate MemberJoinDate = LocalDate.now();


    public MemberDto(Member entity) {
        this.memberIdx = entity.getMemberIdx();
        this.memberId = entity.getMemberId();
        this.memberPw = entity.getMemberPw();
        this.memberName = entity.getMemberName();
        this.memberEmail = entity.getMemberEmail();
        this.memberEmailReceive = entity.getMemberEmailReceive();
        this.memberPwQuestion = entity.getMemberPwQuestion();
        this.memberPwAnswer = entity.getMemberPwAnswer();
        this.memberGender = entity.getMemberGender();
        this.memberBirthDate = entity.getMemberBirthDate();
        this.MemberJoinDate = entity.getMemberJoinDate();
    }
}
