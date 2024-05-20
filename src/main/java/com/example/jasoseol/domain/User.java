package com.example.jasoseol.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private Long id;


    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD",  nullable = false)
    private String password;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "MARKETING", nullable = false)
    private int marketing;

    @Setter
    @Column(name = "CAREER")
    private int career;

    @Column(name = "CREATED_AT", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime created_at;

    @Column(name = "UPDATED_AT", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updated_at = LocalDateTime.now();

    @Column(name="DELETED_AT", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime deleted_at;

    @Column(name="ROLE")
    private String role;

    @Builder
    public User(String email, String password, String nickname, int marketing, int career, String role) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.marketing = marketing;
        this.career = career;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
        this.deleted_at = null;
        this.role = role;

    }
    @Builder
    public User(String email, String password, int marketing){
        this.email = email;
        this.password = password;
        this.nickname = "아무개";
        this.marketing = marketing;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {
        return email;
    }


    @Override
    public String getPassword() {
        return password;
    }

//    public int getCarrer(){ return career; }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
