package com.example.jasoseol.service;

import com.example.jasoseol.domain.Company;
import com.example.jasoseol.dto.AddCompanyRequest;
import com.example.jasoseol.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public boolean addCompany(AddCompanyRequest joinDTO){
        String companyName = joinDTO.getCompanyName();
        boolean isExist = companyRepository.existsByCompanyName(companyName);

        if(isExist){
            return false;
        }
        companyRepository.save(Company.builder()
                .companyName(joinDTO.getCompanyName())
                .site(joinDTO.getSite())
                .companyType(joinDTO.getCompanyType())
                .owner(joinDTO.getOwner())
                .employee(joinDTO.getEmployee())
                .sales(joinDTO.getSales())
                .establishment(joinDTO.getEstablishment())
                .address(joinDTO.getAddress())
                .annualIncome(joinDTO.getAnnualIncome())
                .build()

        );

        return true;
    }
}
