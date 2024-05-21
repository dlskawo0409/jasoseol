package com.example.jasoseol.dto;

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
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    public AddUserRequest() {
        LocalDateTime now = LocalDateTime.now();
        this.nickname = "아무개"; // 닉네임 만들기 함수 필요
        this.created_at = now;
        this.updated_at = now;
        // deleted_at은 객체 생성 시점에서는 일반적으로 null이거나 설정하지 않음
    }


}
