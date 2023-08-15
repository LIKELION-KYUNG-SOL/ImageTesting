package com.springboot.initialize_project.data.repository.Image;

import com.springboot.initialize_project.data.entity.Image;
import com.springboot.initialize_project.data.entity.ImagePicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
