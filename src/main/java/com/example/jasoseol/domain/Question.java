package com.example.jasoseol.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUESTION_ID")
    private Long questionId;

    @Column(name = "QUESTION_CONTENT",nullable = false)
    private String questionContent;

    @Column(name = "LETTER")
    private int letter;

    @ManyToOne
    @JoinColumn(name = "DETAILS_ID")
    private AnnouncementDetails announcementDetails;
}
