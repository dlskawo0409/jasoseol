package com.example.jasoseol.repository;

import com.example.jasoseol.domain.Annoucement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnoucementRepository extends JpaRepository<Annoucement, Long> {
}
