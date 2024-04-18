package com.study.Ex14ReadDB.domain.Community;


import com.study.Ex14ReadDB.domain.Community.Entity.CompanyNotice;
import com.study.Ex14ReadDB.domain.Community.dto.CompanyNoticeDto;
import com.study.Ex14ReadDB.domain.Community.dto.CompanyNoticeListDto;
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


    @Transactional(readOnly = true)
    public List<CompanyNoticeListDto> findAllList(){
        List<CompanyNotice> notices = noticeRepository.findAll();
        List<CompanyNoticeListDto> dtos = notices.stream().map(CompanyNoticeListDto::new).collect(Collectors.toList());

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

    @Transactional(readOnly = true)
    public List<CompanyNoticeListDto> findByTitle(String title){
        List<CompanyNotice> notices = noticeRepository.findByTitleLike(title);

        return notices.stream().map(CompanyNoticeListDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CompanyNoticeListDto> findByContent(String content){
        List<CompanyNotice> notices = noticeRepository.findByContentLike(content);

        return notices.stream().map(CompanyNoticeListDto::new).collect(Collectors.toList());
    }



    @Transactional(readOnly = true)
    public List<CompanyNoticeListDto> findByMemberId(String memberId){
        List<CompanyNotice> notices = noticeRepository.findByMemberIdLike(memberId);

        return notices.stream().map(CompanyNoticeListDto::new).collect(Collectors.toList());
    }
}
