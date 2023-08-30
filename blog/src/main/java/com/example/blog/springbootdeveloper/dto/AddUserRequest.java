package com.example.blog.springbootdeveloper.dto;


import com.example.blog.springbootdeveloper.domain.Article;
import com.example.blog.springbootdeveloper.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    private String email;
    private String password;
    public User toEntity(){
        return User.builder().email(email).password(password).role("user").build();
    }
}

