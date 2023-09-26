package com.example.blog.springbootdeveloper.service;


import com.example.blog.springbootdeveloper.domain.User;
import com.example.blog.springbootdeveloper.dto.AddUserRequest;
import com.example.blog.springbootdeveloper.dto.AddUserResponse;
import com.example.blog.springbootdeveloper.dto.LoginUserRequest;
import com.example.blog.springbootdeveloper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {


    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public AddUserResponse signup(AddUserRequest request){
        User user = request.toEntity(passwordEncoder);
        return AddUserResponse.of(userRepository.save(user));
    }
    public String login(LoginUserRequest request) throws Exception {
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        return user.getUsername();
    }

    public void deleteUser(String email_p){
        Optional<User> user = userRepository.findByEmail(email_p);
        userRepository.delete(user.get());
    }

    @Transactional
    public AddUserResponse changeNickname(String email_p, String nickname) {
        User user = userRepository.findByEmail(email_p).orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
        user.setNickname(nickname);
        return AddUserResponse.of(userRepository.save(user));
    }

    public void withdraw(Long id) { // 회원 탈퇴
        userRepository.deleteById(id);
    }


}
