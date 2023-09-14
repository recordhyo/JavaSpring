//package com.example.blog.springbootdeveloper.dto;
//
//import com.example.blog.springbootdeveloper.domain.User;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//
//public class KakaoToken {
//
//    private String jwtTokenCreate(User kakaoUser) {
//        String TOKEN_TYPE = "BEARER";
//
//        UserDetails userDetails = new UserDetails(kakaoUser);
//        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        UserDetailsImpl userDetails1 = ((UserDetailsImpl) authentication.getPrincipal());
//        final String token = JwtTokenUtils.generateJwtToken(userDetails1);
//        return TOKEN_TYPE + " " + token;
//    }
//
//
//}
