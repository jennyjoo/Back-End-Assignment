package com.study.Ex14ReadDB.domain.Customer.Service;


import com.study.Ex14ReadDB.domain.Customer.Entity.CompanyQna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyQnaRepository extends JpaRepository<CompanyQna, Long> {

    @Query(value = "SELECT * FROM company_qna q", nativeQuery = true)
    List<CompanyQna> findQnasOrderByQnaDateDesc();

    @Query(value = "SELECT * FROM company_qna q WHERE q.qna_title like %:qnaTitle%" +
            " ORDER BY q.qna_date desc", nativeQuery = true)
    List<CompanyQna> findQnasByQnaTitle(String qnaTitle);

    @Query(value = "SELECT * FROM company_qna q WHERE q.qna_content like %:qnaContent%" +
            " ORDER BY q.qna_date desc", nativeQuery = true)
    List<CompanyQna> findQnasByQnaContent(String qnaContent);

    @Query(value = "SELECT * FROM company_qna q WHERE q.qna_name like %:qnaName%" +
            " ORDER BY q.qna_date desc", nativeQuery = true)
    List<CompanyQna> findQnasByQnaName(@Param(value = "qnaName") String qnaName);



}
