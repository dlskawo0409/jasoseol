//package com.example.jasoseol.domain;
//
//import jakarta.persistence.*;
//
//@Entity
//public class interestedCompany {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "INTERESTED_ID", updatable = false)
//    private Long interestedId;
//    @OneToOne
//    @JoinColumn(name = "USER_ID")
//    private User user;
//
//    @OneToMany(mappedBy = "", fetch = FetchType.LAZY)
//    private List<Announcement>
//
//
//}
