package com.example.blog.springbootdeveloper.config;

import com.example.blog.springbootdeveloper.service.OAuthSuccessHandler;
import com.example.blog.springbootdeveloper.service.UserDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig{
    private final UserDetailService userService;

    private final ObjectMapper objectMapper;
//    private final LoginFailureHandler loginFailureHandler;
    private final AuthenticationSuccessHandler loginSuccessHandler;
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring().requestMatchers("/static/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        OAuthSuccessHandler oAuthSuccessHandler = new OAuthSuccessHandler();
        requestCache.setMatchingRequestParameterName(null);
        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeRequests(authorizeRequests -> authorizeRequests.requestMatchers("/login", "/signup", "/api/articles","/oauthlogin").permitAll()
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin.loginPage("/login").defaultSuccessUrl("/api/articles")
                        .usernameParameter("username").passwordParameter("password").successHandler(loginSuccessHandler))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .oauth2Login(oauth2Login -> oauth2Login.defaultSuccessUrl("/api/articles").successHandler(oAuthSuccessHandler))
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/api/articles").invalidateHttpSession(true))
                .csrf(AbstractHttpConfigurer::disable)
                .requestCache((cashe)-> cashe.requestCache(requestCache))
                //.addFilterBefore(jsonUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder encoder, UserDetailService userDetailService) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userService)
                .passwordEncoder(encoder).and().build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
