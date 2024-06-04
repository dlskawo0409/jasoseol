//package com.example.jasoseol.domain;
//
//import jakarta.persistence.*;
//import lombok.Builder;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Builder
//public class Bookmark {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "BOOKMARK_ID")
//    private Long bookmarkId;
//
//    @OneToOne(mappedBy = "bookmark", fetch = FetchType.LAZY)
//    private User user;
//
//    @OneToMany(mappedBy = "bookmark")
//    private final List<Announcement> announcements = new ArrayList<>();
//
//
//}
