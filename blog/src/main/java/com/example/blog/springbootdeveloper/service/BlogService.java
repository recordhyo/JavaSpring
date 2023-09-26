package com.example.blog.springbootdeveloper.service;

import com.example.blog.springbootdeveloper.domain.Article;
import com.example.blog.springbootdeveloper.domain.User;
import com.example.blog.springbootdeveloper.dto.AddArticleRequest;
import com.example.blog.springbootdeveloper.dto.UpdateArticleRequest;
import com.example.blog.springbootdeveloper.repository.BlogRepository;
import com.example.blog.springbootdeveloper.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    @Transactional
    public Article save(AddArticleRequest request, String nickname){
        User user = userRepository.findByNickname(nickname);
        request.setUser(user);

        return blogRepository.save(request.toEntity(nickname));
    }


    @Transactional
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    @Transactional
    public Article findById(long id){
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found:" +id));
    }

    @Transactional
    public void delete(long id){
        blogRepository.deleteById(id);
        SecurityContextHolder.clearContext();
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
        article.update(request.getTitle(), request.getContent(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return article;
    }
}
