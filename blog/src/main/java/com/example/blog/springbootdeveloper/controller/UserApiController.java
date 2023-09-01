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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserApiController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddUserResponse> signup(@RequestBody AddUserRequest request){
        //User signupUser = userRepository.save(request.toEntity(passwordEncoder));
        return ResponseEntity.ok(userService.signup(request));
    }
//    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<User> login(@RequestBody LoginUserRequest request){
//        User signupUser = userRepository.save(request);
//        return ResponseEntity.status(HttpStatus.CREATED).body(signupUser);
//    }
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public String logIn(@RequestBody LoginUserRequest request) {
        try {
            return userService.login(request);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request,response,
                SecurityContextHolder.getContext().getAuthentication());
        return "logout";
    }
}




//@RequiredArgsConstructor
//@RestController
//public class UserApiController {
//    private final UserService userService;
//
//    @PostMapping("/user")
//    public String saveUser(AddUserRequest request){
//        return userService.saveUser(request);
//    }
//    @GetMapping("/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response){
//        new SecurityContextLogoutHandler().logout(request,response,
//                SecurityContextHolder.getContext().getAuthentication());
//        return "redirect:/api/articles";
//    }
//
//}
