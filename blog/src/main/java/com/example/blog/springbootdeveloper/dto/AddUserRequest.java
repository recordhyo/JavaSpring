package com.example.blog.springbootdeveloper.dto;


import com.example.blog.springbootdeveloper.domain.User;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AddUserRequest {
    private String email;
    private String password;
    private String nickname;
    private String provider;
    private LocalDateTime createddate;
    private String email_p;

    public User toEntity(PasswordEncoder passwordEncoder){
        if (password != null) {
            return User.builder()
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .role("USER")
                    .nickname(nickname)
                    .provider(provider)
                    .createddate(LocalDateTime.now())
                    .email_p(email)
                    .build();
        }
        else {
            return User.builder()
                    .email(email)
                    .password(password)
                    .role("USER")
                    .nickname(provider+nickname)
                    .provider(provider)
                    .createddate(LocalDateTime.now())
                    .email_p(email)
                    .build();

        }
    }
}

