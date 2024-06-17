package com.example.jasoseol.domain;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOKMARK_ID")
    private Long bookmarkId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne(mappedBy = "bookmark")
    private Announcement announcement;


}
