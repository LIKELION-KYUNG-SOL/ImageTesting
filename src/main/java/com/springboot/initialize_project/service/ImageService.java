package com.springboot.initialize_project.service;

import com.springboot.initialize_project.data.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    Image addImage(
            Image image,
            List<MultipartFile> files
    ) throws Exception;
}
