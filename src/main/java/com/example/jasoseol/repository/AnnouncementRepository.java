package com.example.jasoseol.repository;

import com.example.jasoseol.domain.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    Announcement findByAnnouncementId(Long announcementId);

    @Query("SELECT am From Announcement am WHERE am.startDay <= :date AND :date <= am.endDay AND am.announcementName LIKE CONCAT('%', :title, '%')")
    List<Announcement> findByDateAndText(@Param("date") String date, @Param("title") String title);
}
