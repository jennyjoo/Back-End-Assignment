package com.study.Ex14ReadDB.domain.CompanyFaq;


import com.study.Ex14ReadDB.domain.CompanyFaq.dto.CompanyFaqDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyFaqRepository extends JpaRepository<CompanyFaq, Long> {

    Optional<CompanyFaq> findCompanyFaqByFaqIdx(Long faqIdx);
}
