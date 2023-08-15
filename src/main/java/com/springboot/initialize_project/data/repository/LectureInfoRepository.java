package com.springboot.initialize_project.data.repository;

import com.springboot.initialize_project.data.entity.LectureInfo;
import com.springboot.initialize_project.data.entity.Post;
import com.springboot.initialize_project.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureInfoRepository extends JpaRepository<LectureInfo, Long> {
    LectureInfo findByPostAndSenior(Post post, User senior);
    List<LectureInfo> findAllBySeniorAndIsComplete(User senior, Boolean isComplete);

}
