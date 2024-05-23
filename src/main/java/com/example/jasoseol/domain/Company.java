package com.example.jasoseol.domain;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPANY_ID")
    private Long companyId;

    @Column(name = "COMPANY_NAME",nullable = false, unique = true)
    private String companyName;

    @Column(name = "SITE")
    private String site;

    @Column(name = "COMPANY_TYPE",nullable = false)
    private int companyType;

    @Column(name = "OWNER" ,nullable = false)
    private String owner;

    @Column(name = "EMPLOYEE",nullable = false)
    private int employee;

    @Column(name = "SALES",nullable = false)
    private long sales;

    @Column(name = "ESTABLISHMENT",nullable = false)
    private int establishment;

    @Column(name = "ADDRESS",nullable = false)
    private String address;

    @Column(name = "ANNUAL_INCOME",nullable = false)
    private long annualIncome;

    @OneToMany(mappedBy = "company")
    private final List<Annoucement> annoucements = new ArrayList<>();
}
