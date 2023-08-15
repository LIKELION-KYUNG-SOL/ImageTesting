package com.springboot.initialize_project.service;

import com.springboot.initialize_project.data.dto.lecture.LectureInfoDto;
import com.springboot.initialize_project.data.dto.post.ResponsePostDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface LectureInfoService {
    LectureInfoDto createLectureInfo(HttpServletRequest servletRequest,
                                     HttpServletResponse servletResponse,
                                     Long postId);

    void updateLectureInfoGiveCarat(HttpServletRequest servletRequest,
                                    HttpServletResponse servletResponse,
                                    Long postId);
    List<ResponsePostDto> getCompletedLectures(HttpServletRequest servletRequest,
                                                               HttpServletResponse servletResponse,
                                                               Boolean isCompleted);
}
