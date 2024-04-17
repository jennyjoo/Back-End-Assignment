package com.study.Ex14ReadDB.domain.Member;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByMemberId(String memberId);

    Optional<Member> findMemberByMemberNameAndMemberEmail(String memberName, String memberEmail);


}
