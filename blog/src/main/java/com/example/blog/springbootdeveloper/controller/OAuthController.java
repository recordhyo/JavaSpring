package com.example.blog.springbootdeveloper.controller;

import net.minidev.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller

public class OAuthController {

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

}
