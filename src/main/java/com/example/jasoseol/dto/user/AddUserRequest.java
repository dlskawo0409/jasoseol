package com.example.jasoseol.dto.user;

import com.example.jasoseol.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class AddUserRequest {
    private String email;
    private String password;
    private String nickname;
    private int marketing;
    private int career;
    private String role;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;
    public User AddUserRequest() {
        LocalDateTime now = LocalDateTime.now();
        this.created_at = now;
        this.updated_at = now;
        return User.builder()
                .email(email)
                .password(password)
                .nickname("아무개")
                .marketing(marketing)
                .career(career)
                .role(role)
                .build();
        // deleted_at은 객체 생성 시점에서는 일반적으로 null이거나 설정하지 않음
    }


}
