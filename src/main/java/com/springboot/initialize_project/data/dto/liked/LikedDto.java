package com.springboot.initialize_project.data.dto.liked;

import com.springboot.initialize_project.data.dto.post.PostDto;
import com.springboot.initialize_project.data.dto.user.UserResponseDto;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikedDto {
    private Long id;
    private PostDto post;
    private UserResponseDto senior;
}


