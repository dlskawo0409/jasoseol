package com.example.jasoseol.dto;

import lombok.Getter;

@Getter
public class AddAnnouncementDetailsRequest {
    private int type;
    private String content;
    private String bigCategory;
    private String middleCategory;
    private String smallCategory;
}
