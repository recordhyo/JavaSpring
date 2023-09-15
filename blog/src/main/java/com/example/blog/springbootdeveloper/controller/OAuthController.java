package com.example.blog.springbootdeveloper.controller;

import com.example.blog.springbootdeveloper.repository.UserRepository;
import com.example.blog.springbootdeveloper.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class OAuthController {
    private final UserRepository userRepository;
    private final OauthService oauthService;

    @Autowired
    public OAuthController(UserRepository userRepository, OauthService oauthService) {
        this.userRepository = userRepository;
        this.oauthService = oauthService;
    }

    @ResponseBody
    @GetMapping("/oauthcurrentuser")
    public JSONObject oauthLoginInfo(Authentication authentication){
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        JSONObject obj = new JSONObject();
        String key = "";
        Object value = null;
        for(Map.Entry<String, Object> entry : attributes.entrySet()){
            key = entry.getKey();
            value = entry.getValue();
            obj.put(key,value);
        }

        return obj;
    }

    @ResponseBody
    @GetMapping("/api/oauth2/kakao")
    public String kakaoCallback(@RequestParam String code){

        String accessToken = oauthService.getKakaoToken(code);
        oauthService.getKakaoInfo(accessToken);

            return accessToken;

    }


}
