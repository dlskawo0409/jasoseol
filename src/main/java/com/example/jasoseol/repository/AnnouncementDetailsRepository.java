package com.example.jasoseol.repository;

import com.example.jasoseol.domain.AnnouncementDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementDetailsRepository extends JpaRepository<AnnouncementDetails, Long> {
    AnnouncementDetails findByAnnouncementDetailsId(Long announcementDetailsId);
}
