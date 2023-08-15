package com.springboot.initialize_project.service;

import com.springboot.initialize_project.data.dto.user.UserResponseDto;
import com.springboot.initialize_project.data.dto.sign.VerifyResponseDto;

public interface UserService {
    UserResponseDto getUserByAccount(String account);
    void deleteUser(String account);
    VerifyResponseDto checkAccountDuplicate(String account);
    VerifyResponseDto checkNicknameDuplicate(String nickname);
}
