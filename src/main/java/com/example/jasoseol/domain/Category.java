package com.example.jasoseol.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "BIG_CATEGORY")
    private String bigCategory;

    @Column(name = "MIDDLE_CATEGORY")
    private String middleCategory;

    @Column(name = "SMALL_CATEGORY")
    private String smailCategory;

    @OneToOne(mappedBy = "category")
    private AnnouncementDetails announcementDetails;

}
