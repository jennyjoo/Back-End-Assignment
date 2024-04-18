package com.study.Ex14ReadDB.domain.Customer.Dto.Request;

import com.study.Ex14ReadDB.domain.Customer.Entity.CompanyOne2one;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestAddOne2oneDto {

    private String name;
    private String tel;
    private String email;
    private String address;
    private String title;
    private String desc;


    public CompanyOne2one toEntity(){

        CompanyOne2one entity = CompanyOne2one.builder()
                .one2oneName(this.name)
                .one2onePhone(this.tel)
                .one2oneEmail(this.email)
                .one2oneAddress(this.address)
                .one2oneTitle(this.title)
                .one2oneContent(this.desc)
                .build();

        return entity;
    }
}
