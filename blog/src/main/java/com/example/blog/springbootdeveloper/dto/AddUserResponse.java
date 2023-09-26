package com.example.blog.springbootdeveloper.dto;

import com.example.blog.springbootdeveloper.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddUserResponse {
    private String email;
    private String name;
    private String nickname;

    public static AddUserResponse of(User user) {
        return AddUserResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .nickname(user.getNickname())
                .build();
    }
}