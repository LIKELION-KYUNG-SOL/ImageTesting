package com.springboot.initialize_project.data.dto.lecture;

import com.springboot.initialize_project.data.dto.post.PostDto;
import com.springboot.initialize_project.data.dto.user.UserResponseDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureInfoDto {
    private Long id;
    private PostDto post;
    private UserResponseDto senior;
    private Boolean isCarat;
}
