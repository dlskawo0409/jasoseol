package com.example.jasoseol.repository;

import com.example.jasoseol.domain.Announcement;
import com.example.jasoseol.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query("SELECT i FROM Image i WHERE i.usable = 1 AND i.main = 1")
    List<Image> findUsableAndMainImages();
}
