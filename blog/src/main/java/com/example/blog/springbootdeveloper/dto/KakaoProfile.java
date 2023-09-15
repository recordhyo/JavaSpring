package com.example.blog.springbootdeveloper.dto;

import lombok.Data;

@Data
public class KakaoProfile {
    private String id;
    private Properties properties;
    private KakaoAccount kakao_account;
    private String email;

    @Data
    class Properties {
        private String nickname;
        private String profile_image;
        private String thumbnail_image;
    }

    @Data
    class KakaoAccount {
        private boolean profile_needs_agreement;
        private Profile profile;
        private String email;

        @Data
        class Profile {
            private String nickname;

        }
    }
}

