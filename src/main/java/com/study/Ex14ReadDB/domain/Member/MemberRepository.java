package com.study.Ex14ReadDB.domain.Member;


import com.study.Ex14ReadDB.domain.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByMemberId(String memberId);

    Optional<Member> findMemberByMemberNameAndMemberEmail(String memberName, String memberEmail);

    Optional<Member> findMemberByMemberIdAndMemberPw(String memberId, String memberPw);

    @Query("SELECT m from Member m WHERE lower(m.memberId) like %:searchKeyword% " +
            "or lower(m.memberEmail) like %:searchKeyword% " +
            "or lower(m.memberName) like %:searchKeyword% " +
            "ORDER BY CASE WHEN upper(:direction) = 'ASC' THEN :order END ASC, " +
            "CASE WHEN upper(:direction) = 'DESC' THEN :order END DESC")
    List<Member> findAllByKeyword(@Param(value = "searchKeyword") String searchKeyword,
                                  @Param(value = "order") String order,
                                  @Param(value = "direction") String direction);

    @Query("SELECT m from Member m WHERE lower(m.memberId) like %:memberId% " +
            "ORDER BY CASE WHEN upper(:direction) = 'ASC' THEN :order END ASC, " +
            "CASE WHEN upper(:direction) = 'DESC' THEN :order END DESC")
    List<Member> findMembersByIdLike(@Param(value = "memberId") String memberId,
                                     @Param(value = "order") String order,
                                     @Param(value = "direction") String direction);

    @Query("SELECT m from Member m where LOWER(m.memberName) like %:memberName% " +
            "ORDER BY CASE WHEN upper(:direction) = 'ASC' THEN :order END ASC, " +
            "CASE WHEN upper(:direction) = 'DESC' THEN :order END DESC")
    List<Member> findMembersByNameLike(@Param(value = "memberName") String memberName,
                                       @Param(value = "order") String order,
                                       @Param(value = "direction") String direction);



    @Query("SELECT m from Member m where LOWER(m.memberEmail) like %:memberEmail% " +
            "ORDER BY CASE WHEN upper(:direction) = 'ASC' THEN :order END ASC, " +
            "CASE WHEN upper(:direction) = 'DESC' THEN :order END DESC")
    List<Member> findMembersByEmailLike(@Param(value = "memberEmail") String memberEmail,
                                        @Param(value = "order") String order,
                                        @Param(value = "direction") String direction);


}
