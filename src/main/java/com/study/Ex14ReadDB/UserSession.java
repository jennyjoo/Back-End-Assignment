package com.study.Ex14ReadDB;


import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class UserSession {
    private Boolean isLogin;
    private String ROLE;
    private Boolean isVerified;
    private Long verifiedIdx;

    public UserSession(){
        this.isLogin = false;
        this.isVerified = false;
        this.ROLE = "USER";
        this.verifiedIdx = null;
    }


    public static UserSession makeUserSession(){
        return UserSession.builder()
                .isLogin(false)
                .isVerified(false)
                .ROLE("USER")
                .verifiedIdx(null)
                .build();
    }

    public static UserSession makeAdminSession(){
        return UserSession.builder()
                .isLogin(false)
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
}
