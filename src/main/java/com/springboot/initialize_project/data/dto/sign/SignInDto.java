package com.springboot.initialize_project.data.dto.sign;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SignInDto {
    String account;
    String password;
}
