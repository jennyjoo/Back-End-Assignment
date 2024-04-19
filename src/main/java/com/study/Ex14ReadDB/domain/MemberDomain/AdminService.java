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

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final MemberRepository memberRepository;
    private final NoticeRepository noticeRepository;

    private static final String[] categories = {"all", "id", "name", "email", "address", "phone"};


    @Transactional
    public CompanyNotice addNotice(CompanyNotice notice){
        return noticeRepository.save(notice);
    }

    @Transactional(readOnly = true)
    public Optional<MemberAdmin> findById(String adminId){
        return adminRepository.findMemberAdminByMemberId(adminId);
    }


    @Transactional(readOnly = true)
    public List<MemberDto> findMembersByCategory(String inputCategory,
                                                    String inputSearchKeyword,
                                                    String orderSelect,
                                                    int pageSelect){

        String category = inputCategory.toLowerCase();
        String searchKeyword = inputSearchKeyword.toLowerCase();

        int idx = orderSelect.lastIndexOf("_");

        String direction = orderSelect.substring(idx + 1);
        String orderCol = orderSelect.substring(0, idx);


        List<Member> members = new ArrayList<>();
        if(category.equals("all")){

            members = findAllByKeyword(searchKeyword, pageSelect);

        }

        if(category.equals("id")){
            members = findMembersByIdLike(searchKeyword, pageSelect);

        }

        if(category.equals("name")){
            members = findMembersByNameLike(searchKeyword, pageSelect);

        }

        if(category.equals("email")){
            members = findMembersByEmailLike(searchKeyword, pageSelect);
        }

        Collections.sort(members, new Comparator<Member>() {
            @Override
            public int compare(Member o1, Member o2) {

                if(orderCol.equals("memberId")){
                    if(direction.toLowerCase().equals("desc")){
                        return o1.getMemberId().compareTo(o2.getMemberId());
                    }
                    return o2.getMemberId().compareTo(o1.getMemberId());
                }

                if(orderCol.equals("memberJoinDate")){
                    if(direction.toLowerCase().equals("desc")){
                        return o1.getMemberJoinDate().compareTo(o2.getMemberJoinDate());
                    }

                    return o2.getMemberJoinDate().compareTo(o1.getMemberJoinDate());
                }
                return 0;
            }
        });

        return members.stream().map(MemberDto::new).collect(Collectors.toList());

    }


    @Transactional(readOnly = true)
    public List<CompanyNoticeDto> findNoticesByCategory(String category,
                                                        String inputSearchKeyword,
                                                        String orderSelect,
                                                        int pageSelect){

        String searchKeyword = inputSearchKeyword.toLowerCase();

        int idx = orderSelect.lastIndexOf("_");

        String direction = orderSelect.substring(idx + 1);
        String orderCol = orderSelect.substring(0, idx);


        List<CompanyNotice> notices = new ArrayList<>();
        if(category.equals("all")){
            notices = findAllNoticesByKeyword(searchKeyword, pageSelect);
            return notices.stream().map(CompanyNoticeDto::new).collect(Collectors.toList());

        }

        if(category.equals("noticeMemberId")){
            notices = findNoticesByMemberIdLike(searchKeyword, pageSelect);
            return notices.stream().map(CompanyNoticeDto::new).collect(Collectors.toList());

        }

        if(category.equals("noticeContent")){
            notices = findNoticesByContentLike(searchKeyword, pageSelect);
            return notices.stream().map(CompanyNoticeDto::new).collect(Collectors.toList());
        }

        if(category.equals("noticeTitle")){
            notices = noticeRepository.findNoticesByTitleLike(searchKeyword);
            return notices.stream().map(CompanyNoticeDto::new).collect(Collectors.toList());
        }

        Collections.sort(notices, new Comparator<CompanyNotice>() {
            @Override
            public int compare(CompanyNotice o1, CompanyNotice o2) {
                if(orderCol.toLowerCase().equals("id")){
                    if(direction.toLowerCase().equals("desc")){
                        return o1.getNoticeMemberId().compareTo(o2.getNoticeMemberId());
                    }
                    return o2.getNoticeMemberId().compareTo(o1.getNoticeMemberId());
                }
                if(orderCol.toLowerCase().equals("noticeDate")){
                    if(direction.toLowerCase().equals("desc")){
                        return o1.getNoticeDate().compareTo(o1.getNoticeDate());
                    }
                    return o2.getNoticeDate().compareTo(o1.getNoticeDate());
                }
                return 0;
            }
        });

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

    private List<CompanyNotice> findAllNoticesByKeyword(String keyword, int pageSize){
        if(pageSize == 5){
            return noticeRepository.findAllByKeywordLimit5(keyword);
        }
        else if(pageSize == 10){
            return noticeRepository.findAllByKeywordLimit10(keyword);
        }

        return noticeRepository.findAllByKeyword(keyword);

    }


    private List<CompanyNotice> findNoticesByMemberIdLike(String keyword, int pageSize){
        if(pageSize == 5){
            return noticeRepository.findNoticesByMemberIdLikeLimit5(keyword);
        }
        else if(pageSize == 10) return noticeRepository.findNoticesByMemberIdLikeLimit10(keyword);


        return noticeRepository.findNoticesByMemberIdLike(keyword);

    }

    private List<CompanyNotice> findNoticesByContentLike(String keyword, int pageSize){
        if(pageSize == 5){
            return noticeRepository.findNoticesByContentLikeLimit5(keyword);
        }
        else if(pageSize == 10){
            return noticeRepository.findNoticesByContentLikeLimit10(keyword);
        }

        return noticeRepository.findNoticesByContentLike(keyword);
    }


    private List<Member> findAllByKeyword(String searchKeyword, int pageSize){
        if(pageSize == 5){
            return memberRepository.findAllByKeywordLimit5(searchKeyword);
        }
        else if(pageSize == 10){
            return memberRepository.findAllByKeywordLimit10(searchKeyword);
        }

        return memberRepository.findAllByKeyword(searchKeyword);


    }

    private List<Member> findMembersByIdLike(String searchKeyword, int pageSize){
        if(pageSize == 5){
            return memberRepository.findMembersByIdLikeLimit5(searchKeyword);
        } else if (pageSize == 10) {

            return memberRepository.findMembersByNameLikeLimit10(searchKeyword);
        }

        return memberRepository.findMembersByIdLike(searchKeyword);
    }

    private List<Member> findMembersByNameLike(String searchKeyword, int pageSize){
        if(pageSize == 5){
            return memberRepository.findMembersByNameLikeLimit5(searchKeyword);
        }
        else if(pageSize == 10){
            return memberRepository.findMembersByNameLikeLimit10(searchKeyword);
        }

        return memberRepository.findMembersByNameLike(searchKeyword);
    }

    private List<Member> findMembersByEmailLike(String searchKeyword, int pageSize){

        if(pageSize == 5){
            return memberRepository.findMembersByEmailLikeLimit5(searchKeyword);
        }
        else if(pageSize == 10){
            return memberRepository.findMembersByEmailLikeLimit10(searchKeyword);
        }

        return memberRepository.findMembersByEmailLike(searchKeyword);

    }



}
