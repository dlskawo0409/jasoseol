package com.example.jasoseol.repository;

import com.example.jasoseol.domain.CompanyUser;
import com.example.jasoseol.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyUserRepository extends JpaRepository<CompanyUser, Long>  {
    CompanyUser findByEmail(String email);

}
