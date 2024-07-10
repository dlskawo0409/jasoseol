package com.example.jasoseol.dto;

import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class AddAnnouncementRequest {
    private String companyName;
    private String announcementTitle;
    private LocalDateTime startDay;
    private LocalDateTime endDay;

}
