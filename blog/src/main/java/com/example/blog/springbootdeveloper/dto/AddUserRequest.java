package com.example.blog.springbootdeveloper.dto;

import com.example.blog.springbootdeveloper.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    private String email;
    private String password;

    public User toUser() {
        return User.builder()
                .email(email)
                .password(password)
                .build();

    }
}
