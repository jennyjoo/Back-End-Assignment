package com.study.Ex14ReadDB.domain.MemberDomain;


import com.study.Ex14ReadDB.domain.Community.Entity.CompanyNotice;
import com.study.Ex14ReadDB.domain.Community.NoticeRepository;
import com.study.Ex14ReadDB.domain.Community.dto.CompanyNoticeDto;
import com.study.Ex14ReadDB.domain.Member.Dto.MemberDto;
import com.study.Ex14ReadDB.domain.Member.Member;
import com.study.Ex14ReadDB.domain.Member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final MemberRepository memberRepository;
    private final NoticeRepository noticeRepository;

    private static final String[] categories = {"all", "id", "name", "email", "address", "phone"};


    @Transactional(readOnly = true)
    public Optional<MemberAdmin> findById(String adminId){
        return adminRepository.findMemberAdminByMemberId(adminId);
    }


    @Transactional(readOnly = true)
    public List<MemberDto> findMembersByCategory(String inputCategory,
                                          String inputSearchKeyword,
                                          String orderSelect){

        String category = inputCategory.toLowerCase();
        String searchKeyword = inputSearchKeyword.toLowerCase();

        int idx = orderSelect.lastIndexOf("_");
        String direction = orderSelect.substring(idx + 1);
        String orderCol = orderSelect.substring(0, idx);
        System.out.println("order col : " + orderCol);
        System.out.println("order dir : " + direction);


        List<Member> members = new ArrayList<>();
        if(category.equals("all")){
            members = memberRepository.findAllByKeyword(searchKeyword, orderCol, direction.toUpperCase());
            return members.stream().map(MemberDto::new).collect(Collectors.toList());

        }

        if(category.equals("id")){
            members = memberRepository.findMembersByIdLike(searchKeyword, orderCol, direction.toUpperCase());
            return members.stream().map(MemberDto::new).collect(Collectors.toList());

        }

        if(category.equals("name")){
            members = memberRepository.findMembersByNameLike(searchKeyword, orderCol, direction.toUpperCase());
            return members.stream().map(MemberDto::new).collect(Collectors.toList());
        }

        if(category.equals("email")){
            members = memberRepository.findMembersByEmailLike(searchKeyword, orderCol, direction.toUpperCase());
            return members.stream().map(MemberDto::new).collect(Collectors.toList());
        }

        return members.stream().map(MemberDto::new).collect(Collectors.toList());

    }


    @Transactional(readOnly = true)
    public List<CompanyNoticeDto> findNoticesByCategory(String category,
                                                 String inputSearchKeyword,
                                                 String orderSelect){

        String searchKeyword = inputSearchKeyword.toLowerCase();

        int idx = orderSelect.lastIndexOf("_");
        String direction = orderSelect.substring(idx + 1);
        String orderCol = orderSelect.substring(0, idx);


        List<CompanyNotice> notices = new ArrayList<>();
        if(category.equals("all")){
            notices = noticeRepository.findAllByKeyword(searchKeyword, orderCol, direction.toUpperCase());
            return notices.stream().map(CompanyNoticeDto::new).collect(Collectors.toList());

        }

        if(category.equals("noticeMemberId")){
            notices = noticeRepository.findNoticesByMemberIdLike(searchKeyword, orderCol, direction.toUpperCase());
            return notices.stream().map(CompanyNoticeDto::new).collect(Collectors.toList());

        }

        if(category.equals("noticeContent")){
            notices = noticeRepository.findNoticesByContentLike(searchKeyword, orderCol, direction.toUpperCase());
            return notices.stream().map(CompanyNoticeDto::new).collect(Collectors.toList());
        }

        if(category.equals("noticeTitle")){
            notices = noticeRepository.findNoticesByTitleLike(searchKeyword, orderCol, direction.toUpperCase());
            return notices.stream().map(CompanyNoticeDto::new).collect(Collectors.toList());
        }

        return notices.stream().map(CompanyNoticeDto::new).collect(Collectors.toList());

    }


    @Transactional(readOnly = true)
    public List<CompanyNoticeDto> findAllNotice(){
        Sort sort = Sort.by(Sort.Direction.DESC, "noticeDate");
        List<CompanyNotice> notices = noticeRepository.findAll(sort);

        return notices.stream().map(CompanyNoticeDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<CompanyNotice> findNoticeById(Long noticeIdx){
        Optional<CompanyNotice> optional = noticeRepository.findById(noticeIdx);

        return optional;

    }

    @Transactional
    public void delete(Long id){
        noticeRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Boolean existsbyNoticeIdx(Long idx){
        return noticeRepository.existsByNoticeIdx(idx);
    }


    @Transactional
    @Modifying
    public CompanyNotice updateNotice(CompanyNotice notice){
        return noticeRepository.save(notice);
    }
}
