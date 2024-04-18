package com.study.Ex14ReadDB.domain.Customer.Service;


import com.study.Ex14ReadDB.domain.Customer.Dto.Request.RequestAddOne2oneDto;
import com.study.Ex14ReadDB.domain.Customer.Entity.CompanyOne2one;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyOne2oneService {
    private final CompanyOne2oneRepository repository;

    @Transactional
    public CompanyOne2one save(RequestAddOne2oneDto dto){
        CompanyOne2one entity = dto.toEntity();
        repository.save(entity);
        return entity;
    }
}
