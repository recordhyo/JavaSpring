package com.example.blog.springbootdeveloper.controller;

import com.example.blog.springbootdeveloper.domain.User;
import net.minidev.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Map;

@Controller
public class SecurityController {
    @GetMapping("/currentuser")
    @ResponseBody
    public JSONObject currentUser(){
        JSONObject obj = new JSONObject();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        obj.put("email", user.getEmail());
        obj.put("nickname", user.getNickname());
        obj.put("provider", user.getProvider());
        return obj;
    }
}

