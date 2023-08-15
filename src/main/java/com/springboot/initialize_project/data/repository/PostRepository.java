package com.springboot.initialize_project.data.repository;

import com.springboot.initialize_project.data.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    //List<Post> findAllBy
}
