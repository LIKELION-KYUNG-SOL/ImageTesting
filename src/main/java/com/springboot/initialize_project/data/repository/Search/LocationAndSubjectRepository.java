package com.springboot.initialize_project.data.repository.Search;

import com.springboot.initialize_project.data.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationAndSubjectRepository extends JpaRepository<Post, Long> {
    List<Post> findByLocationAndSubject(String location, String subject);
}
