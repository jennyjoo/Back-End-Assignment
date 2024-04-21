package com.study.Ex14ReadDB.domain.Member;


import com.study.Ex14ReadDB.domain.Member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


    Boolean existsMemberByMemberId(String memberId);

    @Query(value = "SELECT * FROM company_member m WHERE " +
            " m.member_id like %:searchKeyword% " +
            " OR m.member_name like %:searchKeyword% " +
            " OR m.member_email like %:searchKeyword% ", nativeQuery = true)
    Page<Member> findMembersByAll(@Param(value = "searchKeyword") String searchKeyword, Pageable pageable);


    @Query(value = "SELECT * FROM company_member m WHERE m.member_id like %:searchKeyword% ", nativeQuery = true)
    Page<Member> findMembersByMemberId(@Param(value = "searchKeyword") String searchKeyword, Pageable pageable);


    @Query(value = "SELECT * FROM company_member m WHERE m.member_name like %:searchKeyword% ", nativeQuery = true)
    Page<Member> findMembersByMemberName(@Param(value = "searchKeyword") String searchKeyword, Pageable pageable);

    @Query(value = "SELECT * FROM company_member m WHERE m.member_email like %:searchKeyword% ", nativeQuery = true)
    Page<Member> findMembersByMemberEmail(@Param(value = "searchKeyword") String searchKeyword, Pageable pageable);


    @Query(value = "SELECT * FROM company_member m ORDER BY m.member_idx LIMIT 5", nativeQuery = true)
    List<Member> findAllByLimit5();
    Optional<Member> findMemberByMemberId(String memberId);

    Optional<Member> findMemberByMemberNameAndMemberEmail(String memberName, String memberEmail);

    Optional<Member> findMemberByMemberIdAndMemberPw(String memberId, String memberPw);

    @Query(value = "SELECT * from company_member m WHERE lower(m.member_id) like %:searchKeyword% " +
            "or lower(m.member_email) like %:searchKeyword% " +
            "or lower(m.member_name) like %:searchKeyword% " +
            " limit 20" , nativeQuery = true)
    List<Member> findAllByKeyword(@Param(value = "searchKeyword") String searchKeyword);

    @Query(value = "SELECT * from company_member m WHERE lower(m.member_id) like %:searchKeyword% " +
            "or lower(m.member_email) like %:searchKeyword% " +
            "or lower(m.member_name) like %:searchKeyword% " +
            " limit 5" , nativeQuery = true)
    List<Member> findAllByKeywordLimit5(@Param(value = "searchKeyword") String searchKeyword);

    @Query(value = "SELECT * from company_member m WHERE lower(m.member_id) like %:searchKeyword% " +
            "or lower(m.member_email) like %:searchKeyword% " +
            "or lower(m.member_name) like %:searchKeyword%" +
            " limit 10 ", nativeQuery = true )
    List<Member> findAllByKeywordLimit10(@Param(value = "searchKeyword") String searchKeyword);


    @Query(value = "SELECT * from company_member m WHERE lower(m.member_id) like %:memberId% limit 20 " , nativeQuery = true)
    List<Member> findMembersByIdLike(@Param(value = "memberId") String memberId);
    @Query(value = "SELECT * from company_member m WHERE lower(m.member_id) like %:memberId% " +
            " limit 5", nativeQuery = true)
    List<Member> findMembersByIdLikeLimit5(@Param(value = "memberId") String memberId);

    @Query(value = "SELECT * from company_member m WHERE lower(m.member_id) like %:memberId% " +
            " limit 10", nativeQuery = true)
    List<Member> findMembersByIdLikeLimit10(@Param(value = "memberId") String memberId);


    @Query(value = "SELECT * from company_member m where LOWER(m.member_name) like %:memberName% limit 20 ", nativeQuery = true)
    List<Member> findMembersByNameLike(@Param(value = "memberName") String memberName);
    @Query(value = "SELECT * from company_member m where LOWER(m.member_name) like %:memberName% " +
            " limit 5", nativeQuery = true)
    List<Member> findMembersByNameLikeLimit5(@Param(value = "memberName") String memberName);

    @Query(value = "SELECT * from company_member m where LOWER(m.member_name) like %:memberName% " +
            "limit 10", nativeQuery = true)
    List<Member> findMembersByNameLikeLimit10(@Param(value = "memberName") String memberName);


    @Query(value = "SELECT * from company_member m where LOWER(m.member_email) like %:memberEmail% limit 20 " , nativeQuery = true)
    List<Member> findMembersByEmailLike(@Param(value = "memberEmail") String memberEmail);

    @Query(value = "SELECT * from company_member m where LOWER(m.member_email) like %:memberEmail% " +
            "limit 5", nativeQuery = true)
    List<Member> findMembersByEmailLikeLimit5(@Param(value = "memberEmail") String memberEmail);

    @Query(value = "SELECT * from company_member m where LOWER(m.member_email) like %:memberEmail% " +
            "limit 10", nativeQuery = true)
    List<Member> findMembersByEmailLikeLimit10(@Param(value = "memberEmail") String memberEmail);


}
