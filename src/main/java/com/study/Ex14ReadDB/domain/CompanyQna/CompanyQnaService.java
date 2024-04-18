package com.study.Ex14ReadDB.domain.CompanyQna;


import com.study.Ex14ReadDB.domain.CompanyQna.dto.CompanyQnaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyQnaService {

    private final CompanyQnaRepository repository;

    public List<CompanyQnaDto> findAll(){
        List<CompanyQna> qnas = repository.findAll();
        return qnas.stream().map(CompanyQnaDto::new).collect(Collectors.toList());
    }

    public Optional<CompanyQna> findById(Long id){
        return repository.findById(id);
    }
}
