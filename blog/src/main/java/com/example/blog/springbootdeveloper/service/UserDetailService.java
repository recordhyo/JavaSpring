package com.example.blog.springbootdeveloper.service;

import com.example.blog.springbootdeveloper.domain.User;
import com.example.blog.springbootdeveloper.dto.AddUserRequest;
import com.example.blog.springbootdeveloper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService, OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    //필수로 구현해야 함
    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저"));
        return new User(user.getEmail(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority(user.getRole())).toString(), user.getNickname(), user.getProvider(), user.getCreateddate(), user.getEmail_p());
        //return userRepository.findByEmail(email)
        //        .orElseThrow(() -> new IllegalArgumentException((email)));
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest); // OAuth 서비스(kakao, google, naver)에서 가져온 유저 정보를 담고있음

        String registrationId = userRequest.getClientRegistration()
                .getRegistrationId(); // OAuth 서비스 이름(ex. kakao, naver, google)
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName(); // OAuth 로그인 시 키(pk)가 되는 값
        System.out.println(userNameAttributeName);

        Map<String, Object> attributes = oAuth2User.getAttributes(); // OAuth 서비스의 유저 정보들

        AddUserRequest addUserRequest = OAuthAttributes.extract(registrationId, attributes); // registrationId에 따라 유저 정보를 통해 공통된 UserProfile 객체로 만들어 줌
        addUserRequest.setProvider(registrationId);
        User user = saveOrUpdate(addUserRequest);

        Map<String, Object> customAttribute = customAttribute(attributes, userNameAttributeName, addUserRequest, registrationId);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("USER")),
                customAttribute,
                userNameAttributeName);
    }

    private Map customAttribute(Map attributes, String userNameAttributeName, AddUserRequest addUserRequest, String registrationId) {
        Map<String, Object> customAttribute = new LinkedHashMap<>();
        customAttribute.put(userNameAttributeName, attributes.get(userNameAttributeName));
        customAttribute.put("provider", registrationId);
        customAttribute.put("email", addUserRequest.getEmail());
        customAttribute.put("nickname", addUserRequest.getNickname());
        return customAttribute;

    }

    private User saveOrUpdate(AddUserRequest addUserRequest) {
        User user = userRepository.findByEmail(addUserRequest.getEmail())
                //.map(m -> m.update(addUserRequest.getEmail())) // OAuth 서비스 사이트에서 유저 정보 변경이 있을 수 있기 때문에 우리 DB에도 update
                .orElse(addUserRequest.toEntity(passwordEncoder));

        return userRepository.save(user);
    }
}
