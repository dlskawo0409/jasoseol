package com.example.jasoseol.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Builder;

@Builder
@Entity
public class CompanyUser extends User{

    @Column(name = "COMPANY_NUM")
    private String company_num;

    @Column(name = "COMPANY_USER_NAME")
    private String company_user_name;

    @Column(name = "COMPANY_USER_PHONENUM")
    private String company_user_phonenum;

    @Builder
    CompanyUser(String email, String password, String nickname, int marketing, int career, String role, String company_num ){
        super(email, password, nickname, marketing, career, role);
        this.company_num = company_num;


    }


}
