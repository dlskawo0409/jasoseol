package com.example.jasoseol.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToOne
    @JoinColumn(name = "USER_ID" )
    private CompanyUser companyUser;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private final List<Announcement> announcements = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return companyType == company.companyType && employee == company.employee && sales == company.sales && establishment == company.establishment && annualIncome == company.annualIncome && Objects.equals(companyId, company.companyId) && Objects.equals(companyName, company.companyName) && Objects.equals(site, company.site) && Objects.equals(owner, company.owner) && Objects.equals(address, company.address) && Objects.equals(companyUser, company.companyUser) && Objects.equals(announcements, company.announcements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, companyName, site, companyType, owner, employee, sales, establishment, address, annualIncome, companyUser, announcements);
    }
}
