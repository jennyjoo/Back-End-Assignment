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


    @Query("SELECT n from CompanyNotice n where n.noticeContent like %:content%")
    public List<CompanyNotice> findByContentLike(@Param("content") String content);

    @Query("SELECT n from CompanyNotice n where n.noticeTitle like %:content%")
    public List<CompanyNotice> findByTitleLike(@Param("content") String content);

    @Query("SELECT n from CompanyNotice n where n.noticeMemberId like %:content%")
    public List<CompanyNotice> findByMemberIdLike(@Param("content") String content);



    @Query("SELECT n from CompanyNotice n WHERE lower(n.noticeMemberId) like %:searchKeyword% " +
            "or lower(n.noticeTitle) like %:searchKeyword% " +
            "or lower(n.noticeContent) like %:searchKeyword% " +
            "ORDER BY CASE WHEN upper(:direction) = 'ASC' THEN :order END ASC, " +
            "CASE WHEN upper(:direction) = 'DESC' THEN :order END DESC")
    List<CompanyNotice> findAllByKeyword(@Param(value = "searchKeyword") String searchKeyword,
                                  @Param(value = "order") String order,
                                  @Param(value = "direction") String direction);

    @Query("SELECT n from CompanyNotice n WHERE lower(n.noticeMemberId) like %:noticeMemberId% " +
            "ORDER BY CASE WHEN upper(:direction) = 'ASC' THEN :order END ASC, " +
            "CASE WHEN upper(:direction) = 'DESC' THEN :order END DESC")
    List<CompanyNotice> findNoticesByMemberIdLike(@Param(value = "noticeMemberId") String noticeMemberId,
                                     @Param(value = "order") String order,
                                     @Param(value = "direction") String direction);

    @Query("SELECT n from CompanyNotice n where LOWER(n.noticeContent) like %:noticeContent% " +
            "ORDER BY CASE WHEN upper(:direction) = 'ASC' THEN :order END ASC, " +
            "CASE WHEN upper(:direction) = 'DESC' THEN :order END DESC")
    List<CompanyNotice> findNoticesByContentLike(@Param(value = "noticeContent") String noticeContent,
                                       @Param(value = "order") String order,
                                       @Param(value = "direction") String direction);



    @Query("SELECT n from CompanyNotice n where LOWER(n.noticeTitle) like %:noticeTitle% " +
            "ORDER BY CASE WHEN upper(:direction) = 'ASC' THEN :order END ASC, " +
            "CASE WHEN upper(:direction) = 'DESC' THEN :order END DESC")
    List<CompanyNotice> findNoticesByTitleLike(@Param(value = "noticeTitle") String noticeTitle,
                                        @Param(value = "order") String order,
                                        @Param(value = "direction") String direction);



    Boolean existsByNoticeIdx(Long idx);
}
