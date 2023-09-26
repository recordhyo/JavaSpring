package com.example.blog.springbootdeveloper.controller;


import ch.qos.logback.core.model.Model;
import com.example.blog.springbootdeveloper.domain.Article;
import com.example.blog.springbootdeveloper.domain.User;
import com.example.blog.springbootdeveloper.dto.AddArticleRequest;
import com.example.blog.springbootdeveloper.dto.ArticleResponse;
import com.example.blog.springbootdeveloper.dto.UpdateArticleRequest;
import com.example.blog.springbootdeveloper.service.BlogService;
import com.example.blog.springbootdeveloper.service.OAuthAttributes;
import com.example.blog.springbootdeveloper.service.PrincipalDetails;
import com.example.blog.springbootdeveloper.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogApiController {
    private final BlogService blogService;
    //@AuthenticationPrincipal User user, @AuthenticationPrincipal OAuth2User oAuth2User
    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request, @AuthenticationPrincipal PrincipalDetails principalDetails){
        Article savedArticle = blogService.save(request, principalDetails.getUser().getNickname());
        System.out.println(principalDetails.getAttributes());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
//        if( user != null ) {
//            Article savedArticle = blogService.save(request, user.getNickname());
//            System.out.println(savedArticle.getUser());
//            return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
//        }
//        else {
//            Article savedArticle = blogService.save(request, oAuth2User.getAttributes().get("nickname").toString());
//            System.out.println(oAuth2User.getAttributes());
//            return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
//        }
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles = blogService.findAll().stream().map(ArticleResponse::new).toList();

        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id,  @AuthenticationPrincipal User user){
        Article article = blogService.findById(id);
        return ResponseEntity.ok().body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id){
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id,
        @RequestBody UpdateArticleRequest request){
        Article updateArticle = blogService.update(id, request);
        return ResponseEntity.ok().body(updateArticle);
    }

}
