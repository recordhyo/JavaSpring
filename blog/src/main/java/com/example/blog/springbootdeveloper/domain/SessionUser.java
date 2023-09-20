package com.example.blog.springbootdeveloper.domain;

import java.io.Serializable;

public class SessionUser implements Serializable {

    private String nickname;
    private String email;

    public SessionUser(User user) {
        this.nickname = user.getNickname();
        this.email = user.getEmail();
    }
}
