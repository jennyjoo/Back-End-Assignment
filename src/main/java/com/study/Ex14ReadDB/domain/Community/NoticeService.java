package com.study.Ex14ReadDB.domain.Community;


import com.study.Ex14ReadDB.domain.Community.Entity.CompanyNotice;
import com.study.Ex14ReadDB.domain.Community.dto.CompanyNoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;


    public List<CompanyNoticeDto> findAllNoticesBy(String category, String keyword){
        List<CompanyNoticeDto> dto = null;
        if(category.equals("noticeDate")) {
            dto = noticeRepository.findAllOrderByNoticeDateDesc()
                    .stream()
                    .map(CompanyNoticeDto::new)
                    .collect(Collectors.toList());
        }
        else if(category.equals("noticeTitle")){
            dto = noticeRepository.findAllByNoticeTitle(keyword)
                    .stream()
                    .map(CompanyNoticeDto::new)
                    .collect(Collectors.toList());
        }
        else if(category.equals("noticeContent")){
            dto = noticeRepository.findAllByNoticeContent(keyword)
                    .stream()
                    .map(CompanyNoticeDto::new)
                    .collect(Collectors.toList());
        }
        return dto;

    }

    @Transactional(readOnly = true)
    public List<CompanyNoticeDto> findAllList(){
        List<CompanyNotice> notices = noticeRepository.findAll();
        List<CompanyNoticeDto> dtos = notices.stream().map(CompanyNoticeDto::new).collect(Collectors.toList());

        return dtos;
    }


    @Transactional(readOnly = true)
    public CompanyNoticeDto findByIdx(Long idx){
        Optional<CompanyNotice> optional = noticeRepository.findById(idx);

        if(optional.isPresent()){
            CompanyNoticeDto dto = new CompanyNoticeDto(optional.get());
            return dto;
        }

        CompanyNoticeDto dto = CompanyNoticeDto.builder()
                .noticeTitle("찾을 수 없음")
                .noticeContent("존재하지 않는 게시물입니다")
                .build();

        return dto;
    }

}
