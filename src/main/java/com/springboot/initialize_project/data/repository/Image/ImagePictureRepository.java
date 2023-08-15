package com.springboot.initialize_project.data.repository.Image;

import com.springboot.initialize_project.data.entity.ImagePicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagePictureRepository extends JpaRepository<ImagePicture, Integer> {
    ImagePicture save(ImagePicture imagePicture);
}
