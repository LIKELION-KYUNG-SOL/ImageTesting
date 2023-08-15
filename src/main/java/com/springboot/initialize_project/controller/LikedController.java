package com.springboot.initialize_project.controller;

import com.springboot.initialize_project.config.security.JwtTokenProvider;
import com.springboot.initialize_project.data.dto.liked.RequestLikedDto;
import com.springboot.initialize_project.data.dto.post.ResponsePostDto;
import com.springboot.initialize_project.service.LectureInfoService;
import com.springboot.initialize_project.service.LikedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/liked")
public class LikedController {
    private final LikedService likedService;

    @Autowired
    public LikedController(LikedService likedService){
        this.likedService = likedService;

    }

    @PostMapping()
    public void likedLecture(HttpServletRequest servletRequest,
                             HttpServletResponse servletResponse,
                             @RequestBody RequestLikedDto requestLikedDto){
        likedService.likedLecture(servletRequest, servletResponse,requestLikedDto.getPostId());
    }

    @DeleteMapping()
    public void unLikedLecture(HttpServletRequest servletRequest,
                               HttpServletResponse servletResponse,
                               @RequestBody RequestLikedDto requestLikedDto){
        likedService.deleteLikedLecture(servletRequest, servletResponse,requestLikedDto.getPostId());
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countLikedLecture(HttpServletRequest servletRequest,
                                 HttpServletResponse servletResponse){
        int likeCount = likedService.countLikedLecturesByUser(servletRequest,servletResponse);
        return ResponseEntity.ok(likeCount);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponsePostDto>> getLikedLecture(HttpServletRequest servletRequest,
                                                 HttpServletResponse servletResponse){
        List<ResponsePostDto> responsePostDtos =
                likedService.getLikedLecture(servletRequest,servletResponse);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responsePostDtos);
    }

}
