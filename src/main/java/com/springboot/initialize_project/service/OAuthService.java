package com.springboot.initialize_project.service;

import com.springboot.initialize_project.data.dto.kakao.ResponseKakaoDto;


public interface OAuthService {
    ResponseKakaoDto isUserExists(String email);

}