package com.example.jasoseol.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class MainImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAIN_IMAGE_ID", updatable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "ANNOUCEMENT_ID")
    private Annoucement annoucement;

    @Column(nullable = false)
    private String imagefile;

    @Column(nullable = false)
    private boolean use;


}
