package com.springboot.initialize_project.service.impl;

import com.springboot.initialize_project.data.entity.Image;
import com.springboot.initialize_project.data.entity.ImagePicture;
import com.springboot.initialize_project.data.repository.Image.ImagePictureRepository;
import com.springboot.initialize_project.data.repository.Image.ImageRepository;
import com.springboot.initialize_project.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;

    private ImagePictureRepository imagePictureRepository;

    private FileHandler fileHandler;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, ImagePictureRepository imagePictureRepository, FileHandler fileHandler) {
        this.imageRepository = imageRepository;
        this.imagePictureRepository = imagePictureRepository;
        this.fileHandler = fileHandler;
    }
    @Override
    public Image addImage(
            Image image,
            List<MultipartFile> files
    ) throws Exception {
        List<ImagePicture> list = fileHandler.parseFileInfo(image.getID(), files);

        if(list.isEmpty()){
        }
        else{
            List<ImagePicture> pictureBeans = new ArrayList<>();
            for(ImagePicture imagePicture : list) {
                pictureBeans.add(imagePictureRepository.save(imagePicture));
            }
            image.setPictures(pictureBeans);
        }
        image.setReported_date(new Date().toString());

        return imageRepository.save(image);
    }
}