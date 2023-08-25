package com.example.blog.springbootdeveloper.dto;

import com.example.blog.springbootdeveloper.domain.Article;
import lombok.Getter;

@Getter
public class ArticleResponse {
    private long id;
    private final String title;
    private final String content;

    public ArticleResponse(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }

}
