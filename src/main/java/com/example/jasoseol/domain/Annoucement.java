package com.example.jasoseol.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "annoucement")
public class Annoucement {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANNOUNCEMENT_ID")
    private Long annoucementId;

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

    @OneToMany(mappedBy = "annoucement", fetch = FetchType.LAZY)
    private final List<Image> images = new ArrayList<>();



}
