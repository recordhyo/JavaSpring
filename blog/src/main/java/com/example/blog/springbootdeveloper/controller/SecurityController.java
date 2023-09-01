package com.example.blog.springbootdeveloper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class SecurityController {

    @GetMapping("/currentuser")
    @ResponseBody
    public String currentUser(Principal principal){
        return principal.getName();
    }

}
