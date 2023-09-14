package com.example.blog.springbootdeveloper.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
public class OauthService {

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


        String tokenJson = response.getBody();
        JSONObject jsonObject;
        jsonObject = new JSONObject(tokenJson);
        //System.out.println(jsonObject);
        String accessToken = jsonObject.getString("access_token");
        String refreshToken = jsonObject.getString("refresh_token");

        return accessToken;

    }

//    public void loginWithAccessToken(String token) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Bearer " + token);
//        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//
//        RestTemplate rt = new RestTemplate();
//        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);
//        rt.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
//        rt.setErrorHandler(new DefaultResponseErrorHandler() {
//            public boolean hasError(ClientHttpResponse response) throws IOException {
//                HttpStatus statusCode = (HttpStatus) response.getStatusCode();
//                return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
//            }
//        });
//
//        ResponseEntity<String> response = rt.exchange(
//                "https://kapi.kakao.com/v2/user/me",
//                HttpMethod.POST,
//                kakaoProfileRequest,
//                String.class
//        );
//
//        String str = response.getBody();
//        JSONObject kakao_response = parseJSON(str);
//        JSONObject kakao_account = (JSONObject) kakao_response.get("kakao_account");
//        JSONObject profile = (JSONObject) kakao_account.get("profile");
//        String email = (String) kakao_account.get("email");
//        String nickname = (String) profile.get("nickname");
//
//        KakaoUserInfo kakaoUserInfo = new KakaoUserInfo(email, nickname);
//        if(!userRepository.findByEmail(email).isPresent()){
//            userRepository.save(kakaoUserInfo.toEntity());
//        }
//    }
//
//    private JSONObject parseJSON(String result){
//        try {
//            JSONParser jsonParser = new JSONParser();
//            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
//
//            return jsonObject;
//        } catch(ParseException e){
//            throw new ParseFailedException();
//        }
//    }



    public void getKakaoInfo(String token) {
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            //System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }


            JSONObject jsonObject;
            jsonObject = new JSONObject(result);
            //System.out.println(jsonObject);


            String id = jsonObject.get("id").toString();
            //System.out.println("response body : " + jsonObject.getJSONObject("kakao_account"));
            String email = jsonObject.getJSONObject("kakao_account").getString("email").toString();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
