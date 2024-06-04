package com.example.jasoseol.repository;

import com.example.jasoseol.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByCompanyName(String companyName);
//    Company findBy
    boolean existsByCompanyName(String companyName);
}
