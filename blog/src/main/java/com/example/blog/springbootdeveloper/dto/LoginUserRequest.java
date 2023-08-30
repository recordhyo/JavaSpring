package com.example.blog.springbootdeveloper.dto;

import com.example.blog.springbootdeveloper.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserRequest {
    private String email;
    private String password;

}
