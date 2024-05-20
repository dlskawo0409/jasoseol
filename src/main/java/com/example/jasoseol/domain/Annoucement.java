package com.example.jasoseol.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Annoucement {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANNOUNCEMENT_ID")
    private Long id;

    @Column(name = "START_DAY", nullable = false)
    private LocalDateTime start_day;

    @Column(name = "END_DAY", nullable = false)
    private LocalDateTime end_day;

    @Column(name = "CLICKED", nullable = false)
    private int clicked;

    @Column(name = "BOOKMARK", nullable = false)
    private int bookmark;

    @Column(name = "VISITED", nullable = false)
    private int visited;

}
