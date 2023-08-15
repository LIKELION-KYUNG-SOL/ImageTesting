package com.springboot.initialize_project.data.dto.sign;

import lombok.*;

@Data
@NoArgsConstructor
@ToString
public class SignInResultDto{
    private String token;
    @Builder
    public SignInResultDto(String token){
        this.token = token;
    }
}
