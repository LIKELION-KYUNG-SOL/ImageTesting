package com.springboot.initialize_project.data.dto.post;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePostDto {


    private Long pid;
    private String subject;
    private String location;
    private int contact_time;
    private String available_day;
    private String detail;
    private String question;
    private String answer;
    private boolean is_payable;



}
