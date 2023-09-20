package com.example.blog.springbootdeveloper.controller;

import com.example.blog.springbootdeveloper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class OAuthController {
    private final UserRepository userRepository;

    @Autowired
    public OAuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ResponseBody
    @GetMapping("/oauthcurrentuser")
    public Map<String, Object> oauthLoginInfo(Authentication authentication){
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        return attributes;
    }


}
