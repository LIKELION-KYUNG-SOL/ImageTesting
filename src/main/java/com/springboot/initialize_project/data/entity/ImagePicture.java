package com.springboot.initialize_project.data.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Images_Picture")
public class ImagePicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    @NotNull
    private Integer imageIdx;
    @NotEmpty
    private String original_file_name;
    @NotEmpty
    private String stored_file_path;
    private long file_size;
}
