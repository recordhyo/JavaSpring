package com.example.blog.springbootdeveloper.dto;

import com.example.blog.springbootdeveloper.domain.Article;
import com.example.blog.springbootdeveloper.domain.User;

public class LoginUserResponse {
    private Long id;
    private String email;
    private String password;
    private String role;
    private String Nickname;
    private String email_p;
    public LoginUserResponse(User user){
        this.id = user.getUserId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.Nickname = user.getNickname();
        this.email_p = user.getEmail_p();

    }

}
