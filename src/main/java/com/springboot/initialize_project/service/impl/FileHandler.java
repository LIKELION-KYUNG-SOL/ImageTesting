package com.springboot.initialize_project.service.impl;

import com.springboot.initialize_project.data.entity.ImagePicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class FileHandler {


    private final ServletContext servletContext;

    @Autowired
    public FileHandler(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
    public List<ImagePicture> parseFileInfo(
            Integer imageID,
            List<MultipartFile> multipartFiles
    ) throws Exception {
        List<ImagePicture> fileList = new ArrayList<>();

        if (multipartFiles.isEmpty()) {
            return fileList;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());

        String absolutePath = new File("").getAbsolutePath() + "\\";
        //String absolutePath = System.getProperty("user.dir");

        //String absolutePath = servletContext.getRealPath("/");
        //String path = File.separator + "resources" +File.separator+"static" +File.separator +
                //"lecImage" + File.separator +current_date;

       // String path = "/src/main/resources/static/lecImage/" + current_date;
        String path = "target/classes/static/lecImage/" + current_date;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                String contentType = multipartFile.getContentType();
                String originalFileExtension;
                if (ObjectUtils.isEmpty(contentType)) {
                    break;
                } else {
                    if (contentType.contains("image/jpeg")) {
                        originalFileExtension = ".jpg";
                    } else if (contentType.contains("image/png")) {
                        originalFileExtension = ".png";
                    } else if (contentType.contains("image/gif")) {
                        originalFileExtension = ".gif";
                    } else {
                        break;
                    }
                }

                String new_file_name = Long.toString(System.nanoTime()) + originalFileExtension;
                ImagePicture imagePicture = ImagePicture.builder()
                        .imageIdx(imageID)
                        .original_file_name(multipartFile.getOriginalFilename())
                        .stored_file_path(path + "/" + new_file_name)
                        .file_size(multipartFile.getSize())
                        .build();
                fileList.add(imagePicture);


                file = new File(absolutePath + path + "/" + new_file_name);
                multipartFile.transferTo(file);
            }
        }

        return fileList;
    }

}
