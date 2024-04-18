package com.study.Ex14ReadDB.domain.Member;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<MemberAdmin, Long> {

    Optional<MemberAdmin> findMemberAdminByMemberId(String memberId);
}
