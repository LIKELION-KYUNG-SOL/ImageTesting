package com.springboot.initialize_project.service;

import com.springboot.initialize_project.data.dto.sign.SignInResultDto;
import com.springboot.initialize_project.data.dto.sign.SignUpDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SignService {
    void signUp(String account,
                String password,
                String nickname,
                String phone);
    void signUpUsingDto(SignUpDto signUpDto);
    SignInResultDto signIn(String account, String password,
                           HttpServletRequest servletRequest,
                           HttpServletResponse servletResponse) throws RuntimeException;
    SignInResultDto getNewToken(HttpServletRequest servletRequest, HttpServletResponse servletResponse);
    void deleteToken(HttpServletRequest servletRequest, HttpServletResponse servletResponse, String token);
}
