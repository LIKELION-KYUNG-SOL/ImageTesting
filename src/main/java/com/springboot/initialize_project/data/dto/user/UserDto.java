package com.springboot.initialize_project.data.dto.user;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String account;
    private String password;
    private Boolean isKakao;
    private String nickname;
    private String name;
    private String phone;
    private String role;
}