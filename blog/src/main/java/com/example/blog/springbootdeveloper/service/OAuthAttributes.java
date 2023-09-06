package com.example.blog.springbootdeveloper.service;

import com.example.blog.springbootdeveloper.dto.AddUserRequest;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public enum OAuthAttributes {
    GOOGLE("google", (attributes) -> {
        AddUserRequest addUserRequest = new AddUserRequest();
        System.out.println(attributes);
        addUserRequest.setNickname((String) attributes.get("name"));
        addUserRequest.setEmail((String) attributes.get("email")+"google");
        return addUserRequest;
    }),

    NAVER("naver", (attributes) -> {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        System.out.println(response);
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setNickname((String) response.get("nickname"));
        addUserRequest.setEmail((String) response.get("email")+"naver");
        return addUserRequest;
    }),

    KAKAO("kakao", (attributes) -> {
        // kakao는 kakao_account에 유저정보가 있다. (email)
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        // kakao_account안에 또 profile이라는 JSON객체가 있다. (nickname, profile_image)
        Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");

        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setNickname((String) kakaoProfile.get("nickname"));
        addUserRequest.setEmail((String) kakaoAccount.get("email")+"kakao");
        return addUserRequest;
    });

    private final String registrationId;
    private final Function<Map<String, Object>, AddUserRequest> of;

    OAuthAttributes(String registrationId, Function<Map<String, Object>, AddUserRequest> of) {
        this.registrationId = registrationId;
        this.of = of;
    }

    public static AddUserRequest extract(String registrationId, Map<String, Object> attributes) {
        return Arrays.stream(values())
                .filter(provider -> registrationId.equals(provider.registrationId))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .of.apply(attributes);
    }
}
