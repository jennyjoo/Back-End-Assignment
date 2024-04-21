package com.study.Ex14ReadDB.domain.Customer.Service;


import com.study.Ex14ReadDB.domain.Customer.Entity.CompanyFaq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyFaqRepository extends JpaRepository<CompanyFaq, Long> {

    Optional<CompanyFaq> findCompanyFaqByFaqIdx(Long faqIdx);

    @Query(value="SELECT * FROM company_faq f WHERE" +
            " f.faq_title like %:keyword% " +
            " OR f.faq_content like %:keyword%", nativeQuery = true)
    Page<CompanyFaq> findALLFaqsByKeyword(String keyword, Pageable pageable);
}
