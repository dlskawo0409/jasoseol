package com.example.jasoseol.repository;

import com.example.jasoseol.domain.Annoucement;
import com.example.jasoseol.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
//    Image findByAnnoucement(Annoucement annoucement);
}
