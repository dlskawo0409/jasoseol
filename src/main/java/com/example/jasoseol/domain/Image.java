package com.example.jasoseol.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="image")
public class Image {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMAGE_ID")
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "ANNOUNCEMENT_ID")
    private Announcement announcement;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "USABLE") // USE 예약어를 백틱으로 감싸줍니다.
    private int usable;

    @Column(name ="MAIN")
    private int main;

    @Column(name ="REDIRECTION_URL")
    private String redirectionUrl;
}
