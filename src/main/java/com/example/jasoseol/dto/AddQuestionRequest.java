package com.example.jasoseol.dto;

import lombok.Getter;

@Getter
public class AddQuestionRequest {
    private Long announcementDetailsId;
    private int letter;
    private String question;

}
