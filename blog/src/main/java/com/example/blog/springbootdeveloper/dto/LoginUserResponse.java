package com.example.blog.springbootdeveloper.dto;

import com.example.blog.springbootdeveloper.domain.Article;
import com.example.blog.springbootdeveloper.domain.Role;
import com.example.blog.springbootdeveloper.domain.User;

public class LoginUserResponse {
    private Long id;
    private String email;
    private String password;
    private Role role;
    private String nickname;

    public LoginUserResponse(User user){
        this.id = user.getUserId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = Role.USER;
        this.nickname = user.getNickname();

    }

}
