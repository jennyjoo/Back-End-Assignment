package com.study.Ex14ReadDB.domain.Customer.Service;


import com.study.Ex14ReadDB.domain.Customer.Entity.CompanyOne2one;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyOne2oneRepository extends JpaRepository<CompanyOne2one, Long> {
}
