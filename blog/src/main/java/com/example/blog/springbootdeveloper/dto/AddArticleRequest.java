package com.example.blog.springbootdeveloper.dto;

import com.example.blog.springbootdeveloper.domain.Article;
import com.example.blog.springbootdeveloper.domain.User;
import com.example.blog.springbootdeveloper.service.PrincipalDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddArticleRequest {
    private String title;
    private String content;
    private String createddate;
    private String updateddate;
    private User user;
    private PrincipalDetails principalDetails;


    public Article toEntity(String author){
        if (principalDetails.getAttributes().get("provider") != null) {
            return Article.builder()
                    .title(title)
                    .content(content)
                    .author(author)
                    .createddate(createddate)
                    .updateddate(updateddate)
                    .user(principalDetails.getUser())
                    .build();
        }
        else {
            return Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .createddate(createddate)
                .updateddate(updateddate)
                .user(principalDetails.getUser())
                .build();

        }
    }
}
