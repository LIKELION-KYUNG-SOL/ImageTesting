package com.springboot.initialize_project.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    @Column(nullable = false)
    private String reported_date;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "imageIdx")
    private List<ImagePicture> pictures;

}
