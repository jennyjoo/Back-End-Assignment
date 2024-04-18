package com.study.Ex14ReadDB.domain.Member;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository repository;

    public Optional<MemberAdmin> findById(String adminId){
        return repository.findMemberAdminByMemberId(adminId);
    }
}
