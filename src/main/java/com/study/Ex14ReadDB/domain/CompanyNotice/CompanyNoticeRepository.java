package com.study.Ex14ReadDB.domain.CompanyNotice;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyNoticeRepository extends JpaRepository<CompanyNotice, Long> {


    @Query("SELECT n from CompanyNotice n where n.noticeContent like %:content%")
    public List<CompanyNotice> findByContentLike(@Param("content") String content);

    @Query("SELECT n from CompanyNotice n where n.noticeTitle like %:content%")
    public List<CompanyNotice> findByTitleLike(@Param("content") String content);

    @Query("SELECT n from CompanyNotice n where n.noticeMemberId like %:content%")
    public List<CompanyNotice> findByMemberIdLike(@Param("content") String content);


}
