package com.study.Ex14ReadDB.domain.CompanyQna;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyQnaRepository extends JpaRepository<CompanyQna, Long> {

}
