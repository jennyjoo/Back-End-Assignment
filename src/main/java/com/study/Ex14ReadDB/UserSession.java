package com.study.Ex14ReadDB;


import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class UserSession {
    private Boolean isLogin;
    private String loginId;
    private String ROLE;
    private Boolean isVerified;
    private Long verifiedIdx;

    public UserSession(String loginId){
        this.isLogin = false;
        this.loginId = loginId;
        this.isVerified = false;
        this.ROLE = "USER";
        this.verifiedIdx = null;
    }


    public static UserSession makeUserSession(String loginId){
        return UserSession.builder()
                .isLogin(false)
                .loginId(loginId)
                .isVerified(false)
                .ROLE("USER")
                .verifiedIdx(null)
                .build();
    }

    public static UserSession makeAdminSession(String loginId){
        return UserSession.builder()
                .isLogin(false)
                .loginId(loginId)
                .isVerified(false)
                .ROLE("ADMIN")
                .verifiedIdx(null)
                .build();
    }

    public void login(){
        this.isLogin = true;
    }

    public void verify(Long verifiedIdx){
        this.verifiedIdx = verifiedIdx;
        this.isVerified = true;
    }

    public void invalidateVerification(Long verifiedIdx){
        this.verifiedIdx = null;
        this.isVerified = false;
    }
}
