package com.study.Ex14ReadDB.domain.Member;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
