package com.example.blog.springbootdeveloper.dto;


import com.example.blog.springbootdeveloper.domain.Role;
import com.example.blog.springbootdeveloper.domain.User;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@Getter
@Setter
public class AddUserRequest {
    private String email;
    private String name;
    private String password;
    private String nickname;
    private String provider;
    private LocalDateTime createddate;
    private String email_p;

    public User toEntity(PasswordEncoder passwordEncoder){
        return User.builder()
                .email(email)
                .name(name)
                .password(passwordEncoder.encode(password))
                .role(Role.USER)
                .nickname(nickname)
                .provider(provider)
                .email_p(email+provider)
                .createddate(LocalDateTime.now())
                .build();
    }
//        if (password != null) {
//            return User.builder()
//                    .email(email)
//                    .name(name)
//                    .password(passwordEncoder.encode(password))
//                    .role(Role.USER)
//                    .nickname(nickname)
//                    .provider(provider)
//                    .createddate(LocalDateTime.now())
//                    .build();
//        }
//        else {
//            return User.builder()
//                    .email(email)
//                    .name(name)
//                    .password(password)
//                    .role(Role.USER)
//                    .nickname(nickname)
//                    .provider(provider)
//                    .createddate(LocalDateTime.now())
//                    .build();
//        }

}

