package com.example.blog.springbootdeveloper.service;

import com.example.blog.springbootdeveloper.domain.Role;
import com.example.blog.springbootdeveloper.domain.User;
import com.example.blog.springbootdeveloper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    //필수로 구현해야 함
    @Override
    public User loadUserByUsername(String email_p) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email_p).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저"));
        //return new User(user.getEmail(), passwordEncoder.encode(user.getPassword()), user.getName(), Role.USER, user.getNickname(), user.getProvider(), user.getCreateddate());
        return userRepository.findByEmail(email_p)
                .orElseThrow(() -> new IllegalArgumentException((email_p)));
    }



}
