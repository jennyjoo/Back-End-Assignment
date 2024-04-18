package com.study.Ex14ReadDB.domain.Member;


import com.study.Ex14ReadDB.domain.Member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MemberDto> findAll(){
        Sort sort = Sort.by(Sort.Direction.DESC, "memberIdx");
        List<Member> members = memberRepository.findAll(sort);

        return members.stream().map(MemberDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Member> isMember(String memberId, String memberPw){
        Optional<Member> optional = memberRepository.findMemberByMemberIdAndMemberPw(memberId, memberPw);

        return optional;
    }

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
