package com.example.jasoseol.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
public class AnnouncementDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DETAILS_ID")
    private Long details_id;

    @Column(name = "TYPE", nullable = false)
    private int type;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(name = "WRITERS_COUNT")
    private int writersCount;

    @OneToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "ANNOUNCEMENT_ID")
    private Announcement announcement;

    @OneToMany(mappedBy = "announcementDetails" ,fetch = FetchType.LAZY)
    private final List<Question> questions = new ArrayList<>();

}
