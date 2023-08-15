package com.springboot.initialize_project.service.impl;

import com.springboot.initialize_project.config.security.JwtTokenProvider;
import com.springboot.initialize_project.data.dto.post.PostDto;
import com.springboot.initialize_project.data.dto.post.UpdatePostDto;
import com.springboot.initialize_project.data.entity.Post;
import com.springboot.initialize_project.data.entity.User;
import com.springboot.initialize_project.data.repository.PostRepository;
import com.springboot.initialize_project.data.repository.Search.LocationAndSubjectRepository;
import com.springboot.initialize_project.data.repository.Search.LocationRepository;
import com.springboot.initialize_project.data.repository.Search.SubjectRepository;
import com.springboot.initialize_project.data.repository.UserRepository;
import com.springboot.initialize_project.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final LocationRepository locationRepository;
    private final SubjectRepository subjectRepository;
    private final LocationAndSubjectRepository locationAndSubjectRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public PostServiceImpl(JwtTokenProvider jwtTokenProvider, UserRepository userRepository,
                           PostRepository postRepository, LocationAndSubjectRepository locationAndSubjectRepository,
                           LocationRepository locationRepository, SubjectRepository subjectRepository) {
        this.postRepository = postRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.locationAndSubjectRepository = locationAndSubjectRepository;
        this.locationRepository = locationRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public PostDto getPost(Long pid,HttpServletRequest servletRequest,
                           HttpServletResponse servletResponse) {
        String token = jwtTokenProvider.resolveToken(servletRequest);
        String account = jwtTokenProvider.getAccount(token);

        ModelMapper mapper = new ModelMapper();
        PostDto postDto = new PostDto();

        if(jwtTokenProvider.validateToken(token)){
            User user = userRepository.getByAccount(account);

            Post post = postRepository.findById(pid)
                    .orElseThrow(() -> new EntityNotFoundException("Post not found with PID: " + pid));

            if(user.getId().equals(post.getLecturer().getId())){
                postDto = mapper.map(post, PostDto.class);
            }
        }
        return postDto;
    }

    @Override
    public void savePost(PostDto postDto,
                                    HttpServletRequest servletRequest,
                                    HttpServletResponse servletResponse) {

        String token = jwtTokenProvider.resolveToken(servletRequest);
        String account = jwtTokenProvider.getAccount(token);

        User user = userRepository.getByAccount(account);

        Post insertPost = new Post();
        insertPost.setLecturer(user);
        insertPost.setSubject(postDto.getSubject());
        insertPost.setLocation(postDto.getLocation());
        insertPost.setThumbnail(postDto.getThumbnail());
        insertPost.setStart_time(postDto.getStart_time());
        insertPost.setNumber(postDto.getNumber());
        insertPost.setFinish_time(postDto.getFinish_time());
        insertPost.setAvailable_day(postDto.getAvailable_day());
        insertPost.setDetail(postDto.getDetail());

        insertPost.set_payable(false);

        postRepository.save(insertPost);
    }

    @Override
    public void updatePost(UpdatePostDto updatePostDto,HttpServletRequest servletRequest,
                              HttpServletResponse servletResponse) {

        ModelMapper mapper = new ModelMapper();
        String token = jwtTokenProvider.resolveToken(servletRequest);

        if(jwtTokenProvider.validateToken(token) && postRepository.existsById(updatePostDto.getPid())) {

            String account = jwtTokenProvider.getAccount(token);

            User user = userRepository.getByAccount(account);
            Post post = mapper.map(updatePostDto, Post.class);
            post.setLecturer(user);

            postRepository.save(post);
        }
    }

    @Override
    public void deletePost(Long pid,HttpServletRequest servletRequest,
                           HttpServletResponse servletResponse) throws Exception {
        String token = jwtTokenProvider.resolveToken(servletRequest);
        String account = jwtTokenProvider.getAccount(token);

        if(jwtTokenProvider.validateToken(token)){

            User user = userRepository.getByAccount(account);
            Post post = postRepository.findById(pid)
                    .orElseThrow(() -> new EntityNotFoundException("Post not found with PID: " + pid));

            if(user.getId().equals(post.getLecturer().getId()))
                postRepository.delete(post);

        }
    }

    @Override
    public List<Post> getPostList(Boolean isCompleted,
                                  HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        String token = jwtTokenProvider.resolveToken(servletRequest);
        String account = jwtTokenProvider.getAccount(token);
        return null;
    }

    @Override
    public List<Post> getPostByLocationAndSubject(String location, String subject, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        if ((location == null || location.isEmpty()) && (subject == null || subject.isEmpty())) {
            // 전체 return
            return postRepository.findAll();
        } else if (location == null || location.isEmpty()) {
            // 전체 location and subject 일치
            return subjectRepository.findBySubject(subject);
        } else if (subject == null || subject.isEmpty()) {
            // location 일치 and 전체 subject
            return locationRepository.findByLocation(location);
        } else {
            // location과 subject 모두 일치
            return locationAndSubjectRepository.findByLocationAndSubject(location, subject);
        }
    }
}
