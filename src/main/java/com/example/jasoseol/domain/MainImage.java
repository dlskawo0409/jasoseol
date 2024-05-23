//package com.example.jasoseol.domain;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//
//@Getter
//@Entity
//public class MainImage {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "MAIN_IMAGE_ID", updatable = false)
//    private Long id;
//
//    @OneToOne
//    @JoinColumn(name = "ANNOUCEMENT_ID", nullable = false)
//    private Annoucement annoucement;
//
//    @Column(name = "IMAGE_FILE", nullable = false)
//    private String imageFile;
//
//
//    @Column(name = "USE", nullable = false)
//    private boolean use;
//
//
//}
