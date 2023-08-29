package com.example.blog.springbootdeveloper.service;

import com.example.blog.springbootdeveloper.dto.AddUserRequest;
import com.example.blog.springbootdeveloper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String saveUser(AddUserRequest dto) {
        dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        userRepository.save(dto.toUser());
        return dto.getEmail();
    }
}
