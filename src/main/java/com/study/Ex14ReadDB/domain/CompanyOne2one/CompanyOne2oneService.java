package com.study.Ex14ReadDB.domain.CompanyOne2one;


import com.study.Ex14ReadDB.domain.CompanyOne2one.dto.RequestAddOne2oneDto;
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
