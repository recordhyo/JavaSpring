package com.example.blog.springbootdeveloper.domain;

import java.io.Serializable;

public class SessionUser implements Serializable {

    private String nickname;
    private String email;

    public SessionUser(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }
}
