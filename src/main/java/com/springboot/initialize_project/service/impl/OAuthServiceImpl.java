package com.springboot.initialize_project.service.impl;

import com.springboot.initialize_project.config.security.JwtTokenProvider;
import com.springboot.initialize_project.data.dto.kakao.ResponseKakaoDto;
import com.springboot.initialize_project.data.entity.KakaoUser;
import com.springboot.initialize_project.data.repository.KakaoUserRepository;
import com.springboot.initialize_project.service.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class OAuthServiceImpl implements OAuthService {
    private final KakaoUserRepository kakaoUserRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(OAuthServiceImpl.class);
    public JwtTokenProvider jwtTokenProvider;

    @Autowired
    public OAuthServiceImpl(KakaoUserRepository kakaoUserRepository,
                            JwtTokenProvider jwtTokenProvider) {
        this.kakaoUserRepository = kakaoUserRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public ResponseKakaoDto isUserExists(String email) {
        Optional<KakaoUser> kuser = kakaoUserRepository.findByKakaoEmail(email);

        ResponseKakaoDto responseKakaoDto = new ResponseKakaoDto();

        if(kuser.isPresent()){
            responseKakaoDto.setIs_in(Boolean.TRUE);
            KakaoUser kakaoUser = kuser.get();
            responseKakaoDto.setToken(jwtTokenProvider.createToken(kakaoUser.getKakaoEmail()));
        }else{

            responseKakaoDto.setIs_in(Boolean.FALSE);
        }
        return responseKakaoDto;
    }
}
