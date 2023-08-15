package com.springboot.initialize_project.service.impl;

import com.springboot.initialize_project.config.security.JwtTokenProvider;
import com.springboot.initialize_project.data.dto.post.ResponsePostDto;
import com.springboot.initialize_project.data.entity.Liked;
import com.springboot.initialize_project.data.entity.Post;
import com.springboot.initialize_project.data.entity.User;
import com.springboot.initialize_project.data.repository.LikedRepository;
import com.springboot.initialize_project.data.repository.PostRepository;
import com.springboot.initialize_project.data.repository.UserRepository;
import com.springboot.initialize_project.service.LikedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LikedServiceImpl implements LikedService {

    private UserRepository userRepository;
    private PostRepository postRepository;
    private LikedRepository likedRepository;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public LikedServiceImpl(UserRepository userRepository,
                                  PostRepository postRepository,
                                  LikedRepository likedRepository,
                                  JwtTokenProvider jwtTokenProvider){
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.likedRepository = likedRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @Override
    public void likedLecture(HttpServletRequest servletRequest,
                            HttpServletResponse servletResponse, Long postId) {
        String token = jwtTokenProvider.resolveToken(servletRequest);
        String account = jwtTokenProvider.getAccount(token);

        Post post = postRepository.getById(postId);
        User user = userRepository.getByAccount(account);

        Liked liked = new Liked();

        liked.setSenior(user);
        liked.setPost(post);

        Liked savedLiked = likedRepository.save(liked);

    }

    @Override
    public void deleteLikedLecture(HttpServletRequest servletRequest,
                                   HttpServletResponse servletResponse,
                                   Long postId) {

        String token = jwtTokenProvider.resolveToken(servletRequest);
        String account = jwtTokenProvider.getAccount(token);

        User senior = userRepository.getByAccount(account);
        Post post = postRepository.getById(postId);

        Optional<Liked> liked = likedRepository.findBySeniorAndPost(senior, post);

        if (liked.isPresent()) {
            likedRepository.delete(liked.get());
        }
    }

    @Override
    public int countLikedLecturesByUser(HttpServletRequest servletRequest,
                                        HttpServletResponse servletResponse) {
        String token = jwtTokenProvider.resolveToken(servletRequest);
        String account = jwtTokenProvider.getAccount(token);

        User senior = userRepository.getByAccount(account);
        return likedRepository.countBySenior(senior);
    }

    @Override
    public List<ResponsePostDto> getLikedLecture(HttpServletRequest servletRequest,
                                                 HttpServletResponse servletResponse) {

        String token = jwtTokenProvider.resolveToken(servletRequest);
        String account = jwtTokenProvider.getAccount(token);

        User senior = userRepository.getByAccount(account);

        List<Liked> likedList = likedRepository.findAllBySenior(senior);

        List<ResponsePostDto> likedLectures  = new ArrayList<>();

        for (Liked post : likedList) {
            ResponsePostDto dto = new ResponsePostDto();
            dto.setPid(post.getPost().getPid());
            dto.setSubject(post.getPost().getSubject());

            likedLectures.add(dto);
        }

        return likedLectures;
    }

}
