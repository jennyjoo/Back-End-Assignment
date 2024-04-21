package com.study.Ex14ReadDB.domain.Community;


import com.study.Ex14ReadDB.domain.Community.Entity.CompanyNotice;
import com.study.Ex14ReadDB.domain.Member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<CompanyNotice, Long> {


    @Query(value = "SELECT  * FROM company_notice n WHERE " +
            " n.notice_title like %:searchKeyword% " +
            " OR n.notice_content like %:searchKeyword% " +
            " OR n.notice_member_id like %:searchKeyword%", nativeQuery = true)
    Page<CompanyNotice> findNoticesByAll(@Param(value = "searchKeyword") String searchKeyword, Pageable pageable);
    @Query(value = "SELECT  * FROM company_notice n WHERE n.notice_title like %:searchKeyword% ", nativeQuery = true)
    Page<CompanyNotice> findNoticesByTitle(@Param(value = "searchKeyword") String searchKeyword, Pageable pageable);

    @Query(value = "SELECT  * FROM company_notice n WHERE n.notice_content like %:searchKeyword% ", nativeQuery = true)
    Page<CompanyNotice> findNoticesByContent(@Param(value = "searchKeyword") String searchKeyword, Pageable pageable);

    @Query(value = "SELECT  * FROM company_notice n WHERE n.notice_member_id like %:searchKeyword% ", nativeQuery = true)
    Page<CompanyNotice> findNoticesByMemberId(@Param(value = "searchKeyword") String searchKeyword, Pageable pageable);


    @Query(value = "SELECT * FROM company_notice n ORDER BY n.notice_date desc limit 20 " , nativeQuery = true)
    List<CompanyNotice> findAllOrderByNoticeDateDesc();

    @Query(value = "SELECT * FROM company_notice n WHERE n.notice_title like %:noticeTitle% ORDER BY n.notice_date desc limit 20", nativeQuery = true)
    List<CompanyNotice> findAllByNoticeTitle(String noticeTitle);

    @Query(value = "SELECT * FROM company_notice n WHERE n.notice_content like %:noticeContent% ORDER BY n.notice_date desc limit 20", nativeQuery = true)
    List<CompanyNotice> findAllByNoticeContent(String noticeContent);


    @Query(value = "SELECT * from company_notice as n WHERE lower(n.notice_member_id) like %:searchKeyword% " +
            "or lower(n.notice_title) like %:searchKeyword% " +
            "or lower(n.notice_content) like %:searchKeyword%" +
            " limit 20 " , nativeQuery = true)
    List<CompanyNotice> findAllByKeyword(@Param(value = "searchKeyword") String searchKeyword);
    @Query(value = "SELECT * from company_notice as n WHERE lower(n.notice_member_id) like %:searchKeyword% " +
            "or lower(n.notice_title) like %:searchKeyword% " +
            "or lower(n.notice_content) like %:searchKeyword% " +
            "limit 5", nativeQuery = true)
    List<CompanyNotice> findAllByKeywordLimit5(@Param(value = "searchKeyword") String searchKeyword);

    @Query(value = "SELECT * from company_notice n WHERE lower(n.notice_member_id) like %:searchKeyword% " +
            "or lower(n.notice_title) like %:searchKeyword% " +
            "or lower(n.notice_content) like %:searchKeyword% " +
            "limit 10", nativeQuery = true)
    List<CompanyNotice> findAllByKeywordLimit10(@Param(value = "searchKeyword") String searchKeyword);


    @Query(value = "SELECT * from company_notice n WHERE lower(n.notice_member_id) like %:noticeMemberId% limit 20 ", nativeQuery = true)
    List<CompanyNotice> findNoticesByMemberIdLike(@Param(value = "noticeMemberId") String noticeMemberId);

    @Query(value = "SELECT * from company_notice n WHERE lower(n.notice_member_id) like %:noticeMemberId% limit 5", nativeQuery = true)
    List<CompanyNotice> findNoticesByMemberIdLikeLimit5(@Param(value = "noticeMemberId") String noticeMemberId);

    @Query(value = "SELECT * from company_notice n WHERE lower(n.notice_member_id) like %:noticeMemberId% limit 10", nativeQuery = true)
    List<CompanyNotice> findNoticesByMemberIdLikeLimit10(@Param(value = "noticeMemberId") String noticeMemberId);

    @Query(value = "SELECT * from company_notice n where LOWER(n.notice_content) like %:noticeContent% limit 20", nativeQuery = true )
    List<CompanyNotice> findNoticesByContentLike(@Param(value = "noticeContent") String noticeContent);

    @Query(value = "SELECT * from company_notice n where LOWER(n.notice_content) like %:noticeContent% limit 5", nativeQuery = true )
    List<CompanyNotice> findNoticesByContentLikeLimit5(@Param(value = "noticeContent") String noticeContent);

    @Query(value = "SELECT * from company_notice n where LOWER(n.notice_content) like %:noticeContent% limit 10", nativeQuery = true )
    List<CompanyNotice> findNoticesByContentLikeLimit10(@Param(value = "noticeContent") String noticeContent);



    @Query(value = "SELECT * from company_notice n where LOWER(n.notice_title) like %:noticeTitle% limit 20 " , nativeQuery = true)
    List<CompanyNotice> findNoticesByTitleLike(@Param(value = "noticeTitle") String noticeTitle);

    @Query(value = "SELECT * from company_notice n where LOWER(n.notice_title) like %:noticeTitle% limit 5 " , nativeQuery = true)
    List<CompanyNotice> findNoticesByTitleLikeLimit5(@Param(value = "noticeTitle") String noticeTitle);

    @Query(value = "SELECT * from company_notice n where LOWER(n.notice_title) like %:noticeTitle% limit 10 " , nativeQuery = true)
    List<CompanyNotice> findNoticesByTitleLikeLimit10(@Param(value = "noticeTitle") String noticeTitle);



    Boolean existsByNoticeIdx(Long idx);
}
