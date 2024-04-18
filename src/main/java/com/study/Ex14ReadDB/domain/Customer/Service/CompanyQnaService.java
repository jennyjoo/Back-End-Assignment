package com.study.Ex14ReadDB.domain.Customer.Service;


import com.study.Ex14ReadDB.domain.Customer.Dto.CompanyQnaDto;
import com.study.Ex14ReadDB.domain.Customer.Entity.CompanyQna;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyQnaService {

    private final CompanyQnaRepository repository;

    public List<CompanyQnaDto> findAll(){
        Sort sort = Sort.by(Sort.Direction.DESC, "qnaDate");
        List<CompanyQna> qnas = repository.findAll(sort);
        return qnas.stream().map(CompanyQnaDto::new).collect(Collectors.toList());
    }

    public Optional<CompanyQna> findById(Long id){
        return repository.findById(id);
    }
}
