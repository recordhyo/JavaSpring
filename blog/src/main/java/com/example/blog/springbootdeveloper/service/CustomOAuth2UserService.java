package com.example.blog.springbootdeveloper.service;
import com.example.blog.springbootdeveloper.domain.Role;
import com.example.blog.springbootdeveloper.domain.SessionUser;
import com.example.blog.springbootdeveloper.domain.User;
import com.example.blog.springbootdeveloper.dto.AddUserRequest;
import com.example.blog.springbootdeveloper.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{


    private final UserRepository userRepository;
    private final HttpSession httpsession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("loaduser 실행");

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        //네이버 로그인인지 구글로그인인지 서비스를 구분해주는 코드
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        //OAuth2 로그인 진행시 키가 되는 필드값 프라이머리키와 같은 값 네이버 카카오 지원 x
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        System.out.println(registrationId + userNameAttributeName);

        //OAuth2UserService를 통해 가져온 데이터를 담을 클래스
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        //로그인 한 유저 정보
        User user = saveOrUpdate(attributes);
        System.out.println(user);
        //httpsession.setAttribute("user", new SessionUser(user));

        // 로그인한 유저를 리턴함
        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }



    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                //.map(entity -> entity.update(attributes.getName()))
                .orElse(attributes.toEntity());
        return userRepository.save(user);
    }

}
