package com.study.Ex14ReadDB.domain.Admin.Dto.Request;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestMemberListSortDto {

    private String searchSelect;
    private String searchKeyword;
}
