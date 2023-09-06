package com.example.blog.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long userId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = true)
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "provider", nullable = true)
    private String provider;

    @Getter
    @Column(name = "nickname", unique = true, nullable = true)
    private String nickname;

    @Column(name = "createddate")
    private LocalDateTime createddate;

    @Column(name = "email_p", unique = true, nullable = false)
    private String email_p;


    @Builder
    public User(String email, String password, String role, String nickname, String provider, LocalDateTime createddate, String email_p){
        this.email = email;
        this.password = password;
        this.role = role;
        this.provider = provider;
        this.nickname = nickname;
        this.createddate = LocalDateTime.now();
        this.email_p = email_p;
    }

    //계정의 권한 목록을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("user"));
    }

    //계정의 고유한 값 리턴
    @Override
    public String getUsername(){
        return email_p;
    }

    //계정의 비밀번호 리턴
    @Override
    public String getPassword(){
        return password;
    }


    //계정 만료 여부 리턴
    @Override
    public boolean isAccountNonExpired(){
        return true; //만료안됨
    }

    //계정의 잠김 여부 리턴
    @Override
    public boolean isAccountNonLocked(){
        return true; //true 잠기지 않음
    }

    //비밀번호 만료 여부 리턴
    @Override
    public boolean isCredentialsNonExpired(){
        return true; //만료안됨
    }

    //계정 활성화 여부 리턴
    @Override
    public boolean isEnabled(){
        return true; //활성화 됨
    }

    public User update(String nickname){
        this.nickname = nickname;
        return this;
    }
}
