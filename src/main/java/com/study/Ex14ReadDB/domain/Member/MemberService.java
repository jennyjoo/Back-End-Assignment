package com.study.Ex14ReadDB.domain.Member;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    @Transactional(readOnly = true)
    public Optional<Member> findByMemberId(String memberId){
        Optional<Member> member = memberRepository.findMemberByMemberId(memberId);

        return member;
    }

    @Transactional(readOnly = true)
    public Boolean existsByMemberId(String memberId){
        Optional<Member> member = memberRepository.findMemberByMemberId(memberId);

        if(member.isPresent()){
            return true;
        }
        else return false;
    }

    @Transactional
    public Boolean addMember(Member member){
        Member saved = memberRepository.save(member);

        if(saved == null) return false;
         return true;
    }

    @Transactional(readOnly = true)
    public Optional<Member> findMemberByUserNameAndEmail(String userName, String userEmail){
        Optional<Member> optional = memberRepository.findMemberByMemberNameAndMemberEmail(userName, userEmail);

        return optional;
    }

    @Transactional(readOnly = true)
    public Member findMemberPw(String memberName, String memberEmail, String memberID){
        Optional<Member> optional = memberRepository.findMemberByMemberId(memberID);
        if(optional.isPresent()){
            Member member = optional.get();

            if(member.getMemberEmail().equals(memberEmail)
            && member.getMemberName().equals(memberName)){
                return member;
            }
        }

        return null;
    }

}
