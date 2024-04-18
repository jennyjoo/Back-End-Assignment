package com.study.Ex14ReadDB.domain.Customer.Service;


import com.study.Ex14ReadDB.domain.Customer.Entity.CompanyFaq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyFaqRepository extends JpaRepository<CompanyFaq, Long> {

    Optional<CompanyFaq> findCompanyFaqByFaqIdx(Long faqIdx);
}
