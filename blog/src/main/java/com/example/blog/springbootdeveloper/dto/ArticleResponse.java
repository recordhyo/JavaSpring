package com.example.blog.springbootdeveloper.dto;

import com.example.blog.springbootdeveloper.domain.Article;
import com.example.blog.springbootdeveloper.domain.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleResponse {
    private long id;
    private final String title;
    private final String content;
    private String author;
    private String createddate;
    private String updateddate;
    private User user;

    public ArticleResponse(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.author = article.getAuthor();
        this.createddate = article.getCreateddate();
        this.updateddate = article.getUpdateddate();
        this.user = getUser();

    }

}
