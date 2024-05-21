package com.example.jasoseol.dto;

import lombok.Getter;

@Getter
public class AddCompanyUserRequest extends AddUserRequest{

    private String company_num;
    private String company_user_name;
    private String company_user_phonenum;

    public AddCompanyUserRequest(){
    }

    public String getNickname() {
        return super.getNickname();
    }

    public String getCompanyNum() {
        return this.company_num;
    }

    public String getCompanyUserPhonenum() {
        return this.company_user_phonenum;
    }

    public String getCompanyUserName() {
        return this.company_user_name;
    }
}
