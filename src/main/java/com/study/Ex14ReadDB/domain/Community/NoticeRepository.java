package com.study.Ex14ReadDB.domain.Community;


import com.study.Ex14ReadDB.domain.Community.Entity.CompanyNotice;
import com.study.Ex14ReadDB.domain.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<CompanyNotice, Long> {


//    @Query("SELECT n from CompanyNotice n where n.noticeContent like %:content%")
//    public List<CompanyNotice> findByContentLike(@Param("content") String content);
//
//    @Query("SELECT n from CompanyNotice n where n.noticeTitle like %:content%")
//    public List<CompanyNotice> findByTitleLike(@Param("content") String content);
//
//    @Query("SELECT n from CompanyNotice n where n.noticeMemberId like %:content%")
//    public List<CompanyNotice> findByMemberIdLike(@Param("content") String content);
//
//

//    @Query("SELECT n from CompanyNotice n WHERE lower(n.noticeMemberId) like %:searchKeyword% " +
//            "or lower(n.noticeTitle) like %:searchKeyword% " +
//            "or lower(n.noticeContent) like %:searchKeyword% " +
//            "limit 5")

//    List<CompanyNotice> findAllOrderByNoticeDateDesc();
//    List<CompanyNotice>

    @Query(value = "SELECT * FROM company_notice n ORDER BY n.notice_date desc " , nativeQuery = true)
    List<CompanyNotice> findAllOrderByNoticeDateDesc();

    @Query(value = "SELECT * FROM company_notice n WHERE n.notice_title like %:noticeTitle% ORDER BY n.notice_date desc", nativeQuery = true)
    List<CompanyNotice> findAllByNoticeTitle(String noticeTitle);

    @Query(value = "SELECT * FROM company_notice n WHERE n.notice_content like %:noticeContent% ORDER BY n.notice_date desc", nativeQuery = true)
    List<CompanyNotice> findAllByNoticeContent(String noticeContent);


    @Query(value = "SELECT * from company_notice as n WHERE lower(n.notice_member_id) like %:searchKeyword% " +
            "or lower(n.notice_title) like %:searchKeyword% " +
            "or lower(n.notice_content) like %:searchKeyword% " , nativeQuery = true)
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


    @Query(value = "SELECT * from company_notice n WHERE lower(n.notice_member_id) like %:noticeMemberId% ", nativeQuery = true)
    List<CompanyNotice> findNoticesByMemberIdLike(@Param(value = "noticeMemberId") String noticeMemberId);

    @Query(value = "SELECT * from company_notice n WHERE lower(n.notice_member_id) like %:noticeMemberId% limit 5", nativeQuery = true)
    List<CompanyNotice> findNoticesByMemberIdLikeLimit5(@Param(value = "noticeMemberId") String noticeMemberId);

    @Query(value = "SELECT * from company_notice n WHERE lower(n.notice_member_id) like %:noticeMemberId% limit 10", nativeQuery = true)
    List<CompanyNotice> findNoticesByMemberIdLikeLimit10(@Param(value = "noticeMemberId") String noticeMemberId);

    @Query(value = "SELECT * from company_notice n where LOWER(n.notice_content) like %:noticeContent%", nativeQuery = true )
    List<CompanyNotice> findNoticesByContentLike(@Param(value = "noticeContent") String noticeContent);

    @Query(value = "SELECT * from company_notice n where LOWER(n.notice_content) like %:noticeContent% limit 5", nativeQuery = true )
    List<CompanyNotice> findNoticesByContentLikeLimit5(@Param(value = "noticeContent") String noticeContent);

    @Query(value = "SELECT * from company_notice n where LOWER(n.notice_content) like %:noticeContent% limit 10", nativeQuery = true )
    List<CompanyNotice> findNoticesByContentLikeLimit10(@Param(value = "noticeContent") String noticeContent);



    @Query(value = "SELECT * from company_notice n where LOWER(n.notice_title) like %:noticeTitle% " , nativeQuery = true)
    List<CompanyNotice> findNoticesByTitleLike(@Param(value = "noticeTitle") String noticeTitle);



    Boolean existsByNoticeIdx(Long idx);
}
