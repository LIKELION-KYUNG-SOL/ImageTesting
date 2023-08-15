package com.springboot.initialize_project.data.repository;


import com.springboot.initialize_project.data.entity.Liked;
import com.springboot.initialize_project.data.entity.Post;
import com.springboot.initialize_project.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikedRepository extends JpaRepository<Liked, Long> {


    Optional<Liked> findBySeniorAndPost(User senior, Post post);

    int countBySenior(User senior);

    List<Liked> findAllBySenior(User senior);
}
