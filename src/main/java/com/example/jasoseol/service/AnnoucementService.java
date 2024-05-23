package com.example.jasoseol.service;

import com.example.jasoseol.domain.Annoucement;
import com.example.jasoseol.domain.Company;
import com.example.jasoseol.dto.AddAnnoucementRequest;
import com.example.jasoseol.repository.AnnoucementRepository;
import com.example.jasoseol.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnnoucementService {

    private final AnnoucementRepository annoucementRepository;
    private final CompanyRepository companyRepository;

    public AnnoucementService(AnnoucementRepository annoucementRepository, CompanyRepository companyRepository){
        this.annoucementRepository = annoucementRepository;
        this.companyRepository = companyRepository;
    }

    public boolean addAnnoucement(AddAnnoucementRequest joinDTO){
//        boolean companyExist = companyRepository.existsByCompanyName(joinDTO.getCompanyName());
//        if(!companyExist){
//            return false;
//        }
        System.out.println(joinDTO.getCompanyName());
        Optional<Company> companyOptional = companyRepository.findByCompanyName(joinDTO.getCompanyName());
        if (!companyOptional.isPresent()) {
            System.out.println("Company not found: " + joinDTO.getCompanyName());
            return false;
        }

        annoucementRepository.save(Annoucement.builder()
                .company(companyOptional.get())
                .startDay(joinDTO.getStartDay())
                .endDay(joinDTO.getEndDay())
                .clicked(0)
                .bookmark(0)
                .visited(0)
                .build()
        );
        return true;
    }
}
