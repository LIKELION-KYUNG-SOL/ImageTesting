package com.springboot.initialize_project.controller;

import com.springboot.initialize_project.data.dto.kakao.RequestKakaoDto;
import com.springboot.initialize_project.data.dto.kakao.ResponseKakaoDto;
import com.springboot.initialize_project.service.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

    /**
     * 카카오 callback
     * [GET] /oauth/kakao/callback
     */
    private final Logger LOGGER = LoggerFactory.getLogger(OAuthController.class);

    private final OAuthService oauth;

    @Autowired
    public OAuthController(OAuthService oauth){
        this.oauth = oauth;
    }

    @PostMapping()
    public ResponseEntity<ResponseKakaoDto> isKakao(@RequestBody RequestKakaoDto requestKakaoDto){

        ResponseKakaoDto response = oauth.isUserExists(requestKakaoDto.getKakaoAccount());
        LOGGER.info("결과 account : {}", response.getIs_in());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}