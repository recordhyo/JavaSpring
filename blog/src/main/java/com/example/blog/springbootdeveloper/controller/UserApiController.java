package com.example.blog.springbootdeveloper.controller;

import com.example.blog.springbootdeveloper.domain.User;
import com.example.blog.springbootdeveloper.dto.AddUserRequest;
import com.example.blog.springbootdeveloper.dto.AddUserResponse;
import com.example.blog.springbootdeveloper.dto.LoginUserRequest;
import com.example.blog.springbootdeveloper.dto.LoginUserResponse;
import com.example.blog.springbootdeveloper.repository.UserRepository;
import com.example.blog.springbootdeveloper.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import java.security.Principal;
import java.util.Map;

@Controller
public class UserApiController {

    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login(){
        return "temp";
    }

    @GetMapping("/signup")
    public String signup(){
        return "temp";
    }


    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddUserResponse> signup(@RequestBody AddUserRequest request){
        return ResponseEntity.ok(userService.signup(request));
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> logIn(@RequestBody LoginUserRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        if ("validUsername".equals(email) && "validPassword".equals(password)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request,response,
                SecurityContextHolder.getContext().getAuthentication());
    }



}




