package com.example.jasoseol.dto;

import com.example.jasoseol.Enum.ComePath;
import lombok.Data;

@Data
public class AddCompanyUserRequest {
    private String email;
    private String password;
    private String nickname;
    private int marketing;
    private int career;
    private String companyNum;
    private String companyUserName;
    private String companyUserPhonenum;
    private ComePath comePath;
}
