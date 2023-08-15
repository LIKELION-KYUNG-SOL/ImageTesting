package com.springboot.initialize_project.service;

import com.springboot.initialize_project.data.dto.post.PostDto;
import com.springboot.initialize_project.data.dto.post.UpdatePostDto;
import com.springboot.initialize_project.data.entity.Post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface PostService {
    PostDto getPost(Long pid,
                    HttpServletRequest servletRequest,
                    HttpServletResponse servletResponse);
    void savePost(PostDto postDto,HttpServletRequest servletRequest,
                     HttpServletResponse servletResponse);
    void updatePost(UpdatePostDto updatePostDto,
                       HttpServletRequest servletRequest,
                       HttpServletResponse servletResponse);
    void deletePost(Long pid,
                    HttpServletRequest servletRequest,
                    HttpServletResponse servletResponse) throws Exception;

    List<Post> getPostList(Boolean isCompleted,
                           HttpServletRequest servletRequest,
                           HttpServletResponse servletResponse);

    List<Post> getPostByLocationAndSubject(String location, String subject,
                                 HttpServletRequest servletRequest,
                                 HttpServletResponse servletResponse);


}
