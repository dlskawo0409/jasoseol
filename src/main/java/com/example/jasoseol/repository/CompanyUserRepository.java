package com.example.jasoseol.repository;

import com.example.jasoseol.domain.CompanyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyUserRepository extends JpaRepository<CompanyUser, Long> {
    CompanyUser findByEmail(String Email);
}
