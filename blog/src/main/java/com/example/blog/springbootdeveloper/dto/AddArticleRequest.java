package com.example.blog.springbootdeveloper.dto;

import com.example.blog.springbootdeveloper.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
    private String title;
    private String content;
    private String createddate;
    private String updateddate;


    public Article toEntity(String author){
        return Article.builder()
                .title(title).content(content).author(author)
                .createddate(createddate).updateddate(updateddate)
                .build();
    }
}
