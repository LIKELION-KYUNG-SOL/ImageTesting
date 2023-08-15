package com.springboot.initialize_project.data.repository;

import com.springboot.initialize_project.data.entity.KakaoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KakaoUserRepository extends JpaRepository<KakaoUser, Long> {
    Optional<KakaoUser> findByKakaoEmail(String email);
}
