package com.study.Ex14ReadDB.domain.Member;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "company_member_admin")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberAdmin {


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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate memberJoinDate;



}
