package com.study.Ex14ReadDB.domain.CompanyFaq;


import com.study.Ex14ReadDB.domain.CompanyFaq.dto.CompanyFaqDto;
import com.study.Ex14ReadDB.domain.CompanyOne2one.CompanyOne2one;
import com.study.Ex14ReadDB.domain.CompanyOne2one.dto.RequestAddOne2oneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyFaqService {

    private final CompanyFaqRepository companyFaqRepository;

    @Transactional(readOnly = true)
    public List<CompanyFaqDto> findAll(){

        Sort sort = Sort.by(Sort.Direction.DESC, "faqIdx");

        List<CompanyFaq> faqs = companyFaqRepository.findAll(sort);
        List<CompanyFaqDto> dto = faqs.stream().map(CompanyFaqDto::new).collect(Collectors.toList());

        return dto;
    }

    @Transactional(readOnly = true)
    public CompanyFaqDto findById(Long id){
        Optional<CompanyFaq> optional = companyFaqRepository.findCompanyFaqByFaqIdx(id);

        if(optional.isPresent()){
            CompanyFaqDto dto = new CompanyFaqDto(optional.get());
            return dto;
        }

        CompanyFaqDto dto = CompanyFaqDto.builder()
                .faqContent("삭제된 질문입니다")
                .faqTitle("삭제된 질문입니다")
                .build();

        return dto;
    }

}
