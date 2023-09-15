package com.example.blog.springbootdeveloper.service;

import com.example.blog.springbootdeveloper.domain.User;
import com.example.blog.springbootdeveloper.service.UserDetailService;
import com.example.blog.springbootdeveloper.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class OauthService {
    private final UserRepository userRepository;
    private final UserDetailService userDetailService;

    @Autowired
    public OauthService(UserRepository userRepository, UserDetailService userDetailService) {
        this.userRepository = userRepository;
        this.userDetailService = userDetailService;
    }

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String KAKAO_RESTAPIKEY;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String KAKAO_REDIRECT_URI;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String KAKAO_SECRET_KEY;

    public String getKakaoToken(String code){

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", KAKAO_RESTAPIKEY);
        params.add("redirect_uri", KAKAO_REDIRECT_URI);
        params.add("code",code);
        params.add("client_secret", KAKAO_SECRET_KEY);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String,String>> kakaoTokenReq = new HttpEntity<>(params, headers);
        //System.out.println(params);
        ResponseEntity<String> response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenReq,
                String.class

        );


        //System.out.println(response);
        //System.out.println(response.getClass().getName());

        String tokenJson = response.getBody();
        JSONObject jsonObject;
        jsonObject = new JSONObject(tokenJson);
        //System.out.println(jsonObject);
        String accessToken = jsonObject.getString("access_token");
        String refreshToken = jsonObject.getString("refresh_token");

        return accessToken;

    }

    public HashMap<String, Object> getKakaoInfo(String accesstoken) {
        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + accesstoken); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            JSONObject jsonObject;
            jsonObject = new JSONObject(result);
            //System.out.println(jsonObject);

            JSONObject kakao_account;
            kakao_account = jsonObject.getJSONObject("kakao_account");
            //System.out.println(kakao_account);

            JSONObject properties;
            properties = jsonObject.getJSONObject("properties");
            //System.out.println(properties);

            String id = jsonObject.get("id").toString();
            String email = kakao_account.get("email").toString();
            String nickname = properties.get("nickname").toString();

            userInfo.put("email", email);
            userInfo.put("nickname", nickname);
            userInfo.put("id", Long.parseLong(id));

            System.out.println(userInfo);


            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println(authentication);

        }
        catch (IOException e){
            e.printStackTrace();
        }
        return userInfo;
    }


}
