package com.example.jasoseol.dto;

import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class AddAnnoucementRequest {
    private String companyName;
    private LocalDateTime startDay;
    private LocalDateTime endDay;

}
