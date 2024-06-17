package com.example.jasoseol.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED) // 조인 전략
@DiscriminatorColumn(name = "DTYPE") // DTYPE 열을 사용하여 구분
public class User extends BasicEntity implements UserDetails  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", updatable = false)
    private Long id;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD",  nullable = false)
    private String password;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "MARKETING", nullable = false)
    private int marketing;

    @Column(name = "CAREER")
    private int career;

    @Column(name="ROLE")
    private String role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private final List<Bookmark> bookmarks = new ArrayList<>();


    @Builder
    public User(String email, String password, String nickname, int marketing, int career, String role) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.marketing = marketing;
        this.career = career;
        this.role = role;

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
