package com.springboot.initialize_project.controller;

import com.springboot.initialize_project.data.entity.Image;
import com.springboot.initialize_project.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping("/image")
    public ResponseEntity<?> createImage(
            @Valid @RequestParam("files") List<MultipartFile> files
    ) throws Exception {
        Image image = imageService.addImage(Image.builder()
                .build(), files);

        URI uriLocation = new URI("/image/" + image.getID());
        return ResponseEntity.created(uriLocation).body("{}");
    }
}