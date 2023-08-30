package com.example.blog.springbootdeveloper.dto;


import com.example.blog.springbootdeveloper.domain.User;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AddUserRequest {
    private String email;
    private String password;
    public User toEntity(PasswordEncoder passwordEncoder){
        return User.builder().email(email).password(passwordEncoder.encode(password)).role("user").build();
    }
}

