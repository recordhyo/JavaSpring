package com.example.blog.springbootdeveloper.controller;

import com.example.blog.springbootdeveloper.domain.User;
import com.example.blog.springbootdeveloper.dto.AddUserRequest;
import com.example.blog.springbootdeveloper.dto.AddUserResponse;
import com.example.blog.springbootdeveloper.dto.LoginUserRequest;
import com.example.blog.springbootdeveloper.dto.LoginUserResponse;
import com.example.blog.springbootdeveloper.repository.UserRepository;
import com.example.blog.springbootdeveloper.service.UserDetailService;
import com.example.blog.springbootdeveloper.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@Controller
public class UserApiController {
    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public ResponseEntity<String> logIn(@RequestBody LoginUserRequest request) throws Exception {
        userService.login(request);
        return ResponseEntity.ok("Login successful");
//        String email = request.getEmail();
//        String password = request.getPassword();
//
//        if ("validUsername".equals(email) && "validPassword".equals(password)) {
//            System.out.println("Login successful");
//            return ResponseEntity.ok("Login successful");
//
//        } else {
//            System.out.println("Login failed");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
//        }
    }

    @GetMapping("/currentuser")
    @ResponseBody
    public String currentuser(@AuthenticationPrincipal User user){
        JSONObject object = new JSONObject();
        object.put("email", user.getEmail());
        object.put("nickname", user.getNickname());
        object.put("provider", "null");
        //System.out.println(object);
        return object.toString();
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request,response,
                SecurityContextHolder.getContext().getAuthentication());
    }

//    @DeleteMapping("/deleteuser/{email_p}")
//    public ResponseEntity<Void> deleteuser(@PathVariable String email_p){
//        userService.deleteUser(email_p);
//        return ResponseEntity.ok().build();
//    }
    @PostMapping("/deleteuser")
    public String withdraw(HttpSession session) { // 회원 탈퇴
        Long id = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (id != null) {
            userService.withdraw(id);
        }
        SecurityContextHolder.clearContext(); // SecurityContextHolder에 남아있는 token 삭제
        return "redirect:/api/articles";
    }
    @PostMapping("/nickname")
    public User update(@RequestBody String nickname, @AuthenticationPrincipal User user) {
        user.update(nickname);
        return user;
    }


}




