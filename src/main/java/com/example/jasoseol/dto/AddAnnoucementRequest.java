package com.example.jasoseol.dto;

import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class AddAnnoucementRequest {
    private String CompanyName;
    private LocalDateTime startDay;
    private LocalDateTime endDay;

}
