package com.springboot.initialize_project.data.dto.post;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private String subject;
    private String thumbnail;
    private String location;
    private int number;
    private String start_time;
    private String finish_time;
    private String available_day;
    private String detail;
    private Boolean is_payable;
}




