package com.example.jasoseol.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@Entity
@Table(name = "annoucement")
public class Annoucement {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANNOUNCEMENT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @Column(name = "START_DAY", nullable = false)
    private LocalDateTime startDay;

    @Column(name = "END_DAY", nullable = false)
    private LocalDateTime endDay;

    @Column(name = "CLICKED", nullable = false)
    private int clicked;

    @Column(name = "BOOKMARK", nullable = false)
    private int bookmark;

    @Column(name = "VISITED", nullable = false)
    private int visited;

}
