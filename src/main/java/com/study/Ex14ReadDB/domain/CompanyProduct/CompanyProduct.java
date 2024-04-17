package com.study.Ex14ReadDB.domain.CompanyProduct;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "company_product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long productIdx;

    @Column
    private String productName;

    @Column
    private String productContent;

    @Column
    private String productImg;

    @Column
    private String productRegName;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate productDate;


    @Builder
    public CompanyProduct(String productName, String productContent, String productImg, String productRegName) {
        this.productName = productName;
        this.productContent = productContent;
        this.productImg = productImg;
        this.productRegName = productRegName;
    }

    public void updateDate(LocalDate localDate){
        this.productDate = localDate;
    }

}
