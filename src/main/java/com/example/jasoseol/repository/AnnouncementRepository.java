package com.example.jasoseol.repository;

import com.example.jasoseol.domain.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    Announcement findByAnnouncementId(Long announcementId);
}
