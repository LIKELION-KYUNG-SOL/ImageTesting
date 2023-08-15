package com.springboot.initialize_project.controller;

import com.springboot.initialize_project.config.security.JwtTokenProvider;
import com.springboot.initialize_project.data.dto.lecture.LectureInfoDto;
import com.springboot.initialize_project.data.dto.lecture.RequestLectureInfoDto;
import com.springboot.initialize_project.data.dto.post.ResponsePostDto;
import com.springboot.initialize_project.service.LectureInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/lectureInfo")
public class LectureInfoController {
    private final LectureInfoService lectureInfoService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public LectureInfoController(LectureInfoService lectureInfoService, JwtTokenProvider jwtTokenProvider){
        this.lectureInfoService = lectureInfoService;
        this.jwtTokenProvider 	= jwtTokenProvider;
    }

    @PostMapping()
    public ResponseEntity<LectureInfoDto> createLectureInfo(HttpServletRequest servletRequest,
                                                            HttpServletResponse servletResponse,
                                                            @RequestBody RequestLectureInfoDto requestLectureInfoDto){
        LectureInfoDto lectureInfoDto = lectureInfoService.createLectureInfo(servletRequest, servletResponse, requestLectureInfoDto.getPostId());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(lectureInfoDto);
    }

    @PutMapping()
    public void changeLectureCarat(HttpServletRequest servletRequest,
                                   HttpServletResponse servletResponse,
                                   @RequestBody RequestLectureInfoDto requestLectureInfoDto){
        lectureInfoService.updateLectureInfoGiveCarat(servletRequest, servletResponse, requestLectureInfoDto.getPostId());
    }

    @GetMapping()
    public ResponseEntity<List<ResponsePostDto>> getCompletedLectures(
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse,
            @RequestParam(value = "isCompleted", required = true) Boolean isCompleted){

        List<ResponsePostDto> responsePostDtos =
                lectureInfoService.getCompletedLectures(servletRequest,servletResponse,isCompleted);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responsePostDtos);
    }

}
