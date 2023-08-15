package com.springboot.initialize_project.controller;

import com.springboot.initialize_project.config.security.JwtTokenProvider;
import com.springboot.initialize_project.data.dto.post.PostDto;
import com.springboot.initialize_project.data.dto.post.UpdatePostDto;
import com.springboot.initialize_project.data.entity.Post;
import com.springboot.initialize_project.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public PostController(PostService postService, JwtTokenProvider jwtTokenProvider){
        this.postService=postService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping(value = "/{pid}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long pid, HttpServletRequest servletRequest,
                                           HttpServletResponse servletResponse){
        //String account = jwtTokenProvider.getAccount(jwtTokenProvider.resolveToken(servletRequest));

        PostDto selectedpostDto = postService.getPost(pid, servletRequest, servletResponse);
        return ResponseEntity.status(HttpStatus.OK).body(selectedpostDto);
    }

    @PostMapping()
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, HttpServletRequest servletRequest,
                                              HttpServletResponse servletResponse){

        //String account = jwtTokenProvider.getAccount(jwtTokenProvider.resolveToken(servletRequest));
        postService.savePost(postDto, servletRequest, servletResponse);
        //PostDto savedpostDto = postService.savePost(postDto);

        return ResponseEntity.status(HttpStatus.OK).body(postDto);

    }

    @PutMapping()
    public void updatePost(@RequestBody UpdatePostDto updatePostDto, HttpServletRequest servletRequest,
                           HttpServletResponse servletResponse) throws Exception{

        //String account = jwtTokenProvider.getAccount(jwtTokenProvider.resolveToken(servletRequest));
        //PostDto updatedPostDto = postService.updatePost(updatePostDto);

        postService.updatePost(updatePostDto, servletRequest, servletResponse);
    }

    @DeleteMapping()
    public ResponseEntity<String> deletePost(@RequestParam(value = "pid", required = true) Long pid,
                                             HttpServletRequest servletRequest,
                                             HttpServletResponse servletResponse) throws Exception{
        //String account = jwtTokenProvider.getAccount(jwtTokenProvider.resolveToken(servletRequest));

        postService.deletePost(pid, servletRequest, servletResponse);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }


    @GetMapping("/posts")
    public List<Post> getPostsByLocationAndSubject(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String subject,
            HttpServletRequest request,
            HttpServletResponse response) {

        return postService.getPostByLocationAndSubject(location, subject, request, response);
    }



}
