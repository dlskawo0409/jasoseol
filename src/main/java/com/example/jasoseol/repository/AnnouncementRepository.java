package com.example.jasoseol.repository;

import com.example.jasoseol.domain.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    Announcement findByAnnouncementId(Long announcementId);

    @Query("SELECT am.company.companyName, am.announcementTitle FROM Announcement am " +
            "WHERE :date BETWEEN am.startDay AND am.endDay AND (am.announcementTitle LIKE %:text% OR am.company.companyName LIKE %:text%)")
    List<Object[]> findByDateAndText(@Param("date") LocalDateTime date, @Param("text") String text);


}
