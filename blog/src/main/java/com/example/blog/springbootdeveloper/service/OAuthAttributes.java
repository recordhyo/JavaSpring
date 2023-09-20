package com.example.blog.springbootdeveloper.service;


import com.example.blog.springbootdeveloper.domain.Role;
import com.example.blog.springbootdeveloper.domain.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String nickname;
    private String email;
    private String provider;


    @Builder
    public OAuthAttributes(Map<String,Object> attributes, String nameAttributeKey, String nickname, String email, String provider){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.nickname = nickname;
        this.email = email;
        this.provider = provider;

    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String,Object> attributes){
        //네이버 로그인 인지 판단.
        if("naver".equals(registrationId)){
            return ofNaver("id",attributes);
        } else if ("kakao".equals(registrationId)) {
            return ofKakao("id",attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        // 응답 받은 사용자의 정보를 Map형태로 변경.
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        // 미리 정의한 속성으로 빌드.
        return OAuthAttributes.builder()
                .nickname("naver"+response.get("nickname").toString())
                .email((String) response.get("email"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .provider("naver")
                .build();
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        System.out.println(attributes);
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");
        // 미리 정의한 속성으로 빌드.
        return OAuthAttributes.builder()
                .nickname("kakao"+kakaoProfile.get("nickname").toString())
                .email((String) kakaoAccount.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .provider("kakao")
                .build();
    }

    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String,Object> attributes){
        // 미리 정의한 속성으로 빌드.
        return OAuthAttributes.builder()
                .nickname((String) attributes.get("nickname"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .provider("google")
                .build();
    }
    public User toEntity(){
        return User.builder()
                .email(email)
                .role(Role.USER)
                .nickname(nickname)
                .provider(provider)
                .createddate(LocalDateTime.now())
                .build();
    }
}
