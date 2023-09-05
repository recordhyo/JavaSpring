package com.example.blog.springbootdeveloper.repository;

import com.example.blog.springbootdeveloper.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmailAndProvider(String email, String provider);
}
