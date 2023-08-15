package com.springboot.initialize_project.data.dto.sign;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SignUpDto {
    private String account;
    private String password;
    private String role;
    private String nickname;
    private String name;
    private String phone;
    private Boolean isKakao;
}
