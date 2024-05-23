package com.example.jasoseol.dto;

import lombok.Getter;
import org.springframework.boot.SpringApplication;
@Getter
public class AddCompanyRequest {
    private String companyName;
    private String site;
    private int companyType;
    private  String owner;
    private int employee;
    private long sales;
    private int establishment;
    private String address;
    private long annualIncome;
}
