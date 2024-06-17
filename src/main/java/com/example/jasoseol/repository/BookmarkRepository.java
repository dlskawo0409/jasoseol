package com.example.jasoseol.repository;

import com.example.jasoseol.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Bookmark findByUserId(Long userId);
}
