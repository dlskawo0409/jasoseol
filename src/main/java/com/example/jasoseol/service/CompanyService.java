package com.example.jasoseol.service;

import com.example.jasoseol.domain.Company;
import com.example.jasoseol.domain.CompanyUser;
import com.example.jasoseol.dto.AddCompanyRequest;
import com.example.jasoseol.repository.CompanyRepository;
import com.example.jasoseol.repository.CompanyUserRepository;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyUserRepository companyUserRepository;

    public CompanyService(CompanyRepository companyRepository, CompanyUserRepository companyUserRepository){
        this.companyRepository = companyRepository;
        this.companyUserRepository = companyUserRepository;
    }

    public boolean addCompany(AddCompanyRequest joinDTO, CompanyUser companyUser){
        String companyName = joinDTO.getCompanyName();
        boolean isExist = companyRepository.existsByCompanyName(companyName);

        if(isExist){
            return false;
        }
        Company company = Company.builder()
                .companyName(joinDTO.getCompanyName())
                .site(joinDTO.getSite())
                .companyType(joinDTO.getCompanyType())
                .owner(joinDTO.getOwner())
                .employee(joinDTO.getEmployee())
                .sales(joinDTO.getSales())
                .establishment(joinDTO.getEstablishment())
                .address(joinDTO.getAddress())
                .annualIncome(joinDTO.getAnnualIncome())
                .companyUser(companyUser)
                .build();
        companyRepository.save(company);
        companyUser.setCompany(company);
        companyUserRepository.save(companyUser);

        return true;
    }

//    public boolean changeCompanyName(String beforeName, String afterName){
//        Optional<Company> companyOptional = companyRepository.findByCompanyName(beforeName);
//        if (!companyOptional.isPresent()) {
//            return false;
//        }
//        Company company = companyOptional.get();
//        company.set
//
//        return true;
//
//    }

//    public boolean addCompany(AddCompanyRequest joinDTO){
//        String companyName = joinDTO.getCompanyName();
//        boolean isExist = companyRepository.existsByCompanyName(companyName);
//
//        if(isExist){
//            return false;
//        }
//        companyRepository.save(Company.builder()
//                .companyName(joinDTO.getCompanyName())
//                .site(joinDTO.getSite())
//                .companyType(joinDTO.getCompanyType())
//                .owner(joinDTO.getOwner())
//                .employee(joinDTO.getEmployee())
//                .sales(joinDTO.getSales())
//                .establishment(joinDTO.getEstablishment())
//                .address(joinDTO.getAddress())
//                .annualIncome(joinDTO.getAnnualIncome())
//
//                .build()
//
//        );
//
//        return true;
//    }

//    public boolean changeCompanyName(String companyName){
//        Company company = companyName.
//    }
}
