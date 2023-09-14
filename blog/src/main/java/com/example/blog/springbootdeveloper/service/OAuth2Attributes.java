package com.example.blog.springbootdeveloper.service;

import com.example.blog.springbootdeveloper.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;
@Getter
public class OAuth2Attributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String nickname;
    private String email;
    private String password = null;
    private String provider;


    @Builder
    public OAuth2Attributes(Map<String,Object> attributes, String nameAttributeKey, String nickname, String email, String picture){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.nickname = nickname;
        this.email = email;

    }

    public static OAuth2Attributes of(String registrationId, String userNameAttributeName, Map<String,Object> attributes){
        //네이버 로그인 인지 판단.
        if("naver".equals(registrationId)){
            return ofNaver("id",attributes);
        } else if ("kakao".equals(registrationId)) {
            return ofKakao("id",attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuth2Attributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        // 응답 받은 사용자의 정보를 Map형태로 변경.
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        // 미리 정의한 속성으로 빌드.
        return OAuth2Attributes.builder()
                .nickname((String) response.get("nickname"))
                .email((String) response.get("email"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuth2Attributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");
        // 미리 정의한 속성으로 빌드.
        return OAuth2Attributes.builder()
                .nickname((String) kakaoProfile.get("nickname"))
                .email((String) kakaoProfile.get("email"))
                .attributes(kakaoProfile)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public static OAuth2Attributes ofGoogle(String userNameAttributeName, Map<String,Object> attributes){
        // 미리 정의한 속성으로 빌드.
        return OAuth2Attributes.builder()
                .nickname((String) attributes.get("nickname"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
    public User toEntity(){
        return User.builder()
                .email(email)
                .password(password)
                .role("USER")
                .nickname(nickname)
                .createddate(LocalDateTime.now())
                .email_p(email)
                .build();
    }

}
