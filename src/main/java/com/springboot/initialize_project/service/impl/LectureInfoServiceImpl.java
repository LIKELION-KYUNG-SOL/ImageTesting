package com.springboot.initialize_project.service.impl;

import com.springboot.initialize_project.config.security.JwtTokenProvider;
import com.springboot.initialize_project.data.dto.lecture.LectureInfoDto;
import com.springboot.initialize_project.data.dto.post.ResponsePostDto;
import com.springboot.initialize_project.data.entity.LectureInfo;
import com.springboot.initialize_project.data.entity.Post;
import com.springboot.initialize_project.data.entity.User;
import com.springboot.initialize_project.data.repository.LectureInfoRepository;
import com.springboot.initialize_project.data.repository.PostRepository;
import com.springboot.initialize_project.data.repository.UserRepository;
import com.springboot.initialize_project.service.LectureInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class LectureInfoServiceImpl implements LectureInfoService {

    private UserRepository userRepository;
    private PostRepository postRepository;
    private LectureInfoRepository lectureInfoRepository;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public LectureInfoServiceImpl(UserRepository userRepository,
                                  PostRepository postRepository,
                                  LectureInfoRepository lectureInfoRepository,
                                  JwtTokenProvider jwtTokenProvider){
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.lectureInfoRepository = lectureInfoRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public LectureInfoDto createLectureInfo(HttpServletRequest servletRequest,
                                            HttpServletResponse servletResponse, Long postId) {

        String token = jwtTokenProvider.resolveToken(servletRequest);
        String account = jwtTokenProvider.getAccount(token);

        LectureInfo lectureInfo = new LectureInfo();

        Post post = postRepository.getById(postId);

        User user = userRepository.getByAccount(account);

        lectureInfo.setPost(post);
        lectureInfo.setSenior(user);
        lectureInfo.setIsCarat(false);
        lectureInfo.setIsComplete(false);

        LectureInfo savedLectureInfo = lectureInfoRepository.save(lectureInfo);

        ModelMapper mapper = new ModelMapper();
        LectureInfoDto lectureInfoDto = mapper.map(savedLectureInfo, LectureInfoDto.class);

        return lectureInfoDto;
    }

    @Override
    public void updateLectureInfoGiveCarat(HttpServletRequest servletRequest,
                                           HttpServletResponse servletResponse,
                                           Long postId) {

        String token = jwtTokenProvider.resolveToken(servletRequest);
        String account = jwtTokenProvider.getAccount(token);

        User user = userRepository.getByAccount(account);
        Post post = postRepository.getById(postId);

        LectureInfo lectureInfo = lectureInfoRepository.findByPostAndSenior(post, user);
        lectureInfo.setIsCarat(true);
        lectureInfoRepository.save(lectureInfo);
    }

    @Override
    public List<ResponsePostDto> getCompletedLectures(HttpServletRequest servletRequest,
                                                      HttpServletResponse servletResponse,
                                                      Boolean isCompleted) {

        String token = jwtTokenProvider.resolveToken(servletRequest);
        String account = jwtTokenProvider.getAccount(token);

        User user = userRepository.getByAccount(account);

        List<LectureInfo> savedPosts = lectureInfoRepository.findAllBySeniorAndIsComplete(user, isCompleted);
        List<ResponsePostDto> returnedPosts = new ArrayList<>();

        for(LectureInfo post : savedPosts){
            ResponsePostDto tmp = new ResponsePostDto();
            tmp.setPid(post.getPost().getPid());
            tmp.setSubject(post.getPost().getSubject());

            returnedPosts.add(tmp);
        }

        return returnedPosts;
    }
}
