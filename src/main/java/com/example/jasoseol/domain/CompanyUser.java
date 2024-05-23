package com.example.jasoseol.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Entity
@DiscriminatorValue("COMPANY_USER")
public class CompanyUser extends User {

    @Column(name = "COMPANY_NUM")
    private String companyNum;

    @Column(name = "COMPANY_USER_NAME")
    private String companyUserName;

    @Column(name = "COMPANY_USER_PHONENUM")
    private String companyUserPhonenum;

    @Builder(builderMethodName = "companyUserBuilder")
    public CompanyUser(String email, String password, String nickname, int marketing, int career, String role, String companyNum, String companyUserName, String companyUserPhonenum) {
        super(email, password, nickname, marketing, career, role);
        this.companyNum = companyNum;
        this.companyUserName = companyUserName;
        this.companyUserPhonenum = companyUserPhonenum;
    }
}
