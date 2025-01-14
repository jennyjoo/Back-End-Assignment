package com.study.Ex14ReadDB.domain.Member.Dto.Request;

import com.study.Ex14ReadDB.domain.Member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestJoinDto {
    private String userID;
    private String userPW;
    private String userName;
    private String emailID;
    private String emailDomain;
    private Boolean receive;
    private Integer passwordCheckQuestion;
    private String passwordCheckAnswer;
    private String gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate memberBirthDate;

    public Member toEntity(){
        Member member = Member.builder()
                .memberId(userID)
                .memberPw(userPW)
                .memberName(userName)
                .memberPwQuestion(this.passwordCheckQuestion)
                .memberPwAnswer(this.passwordCheckAnswer)
                .memberEmailReceive((receive) ? 1 : 0)
                .memberEmail(this.emailID + "@" + this.emailDomain)
                .memberGender(this.gender)
                .memberBirthDate(this.memberBirthDate)
                .build();

        return member;
    }

}
