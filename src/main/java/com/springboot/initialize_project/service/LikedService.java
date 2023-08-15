package com.springboot.initialize_project.service;

import com.springboot.initialize_project.data.dto.post.ResponsePostDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface LikedService {
    void likedLecture(HttpServletRequest servletRequest,
                     HttpServletResponse servletResponse, Long postId);

    void deleteLikedLecture(HttpServletRequest servletRequest,
                            HttpServletResponse servletResponse,
                            Long postId);

    int countLikedLecturesByUser(HttpServletRequest servletRequest,
                                 HttpServletResponse servletResponse);

    List<ResponsePostDto> getLikedLecture(HttpServletRequest servletRequest,
                                          HttpServletResponse servletResponse);
}
