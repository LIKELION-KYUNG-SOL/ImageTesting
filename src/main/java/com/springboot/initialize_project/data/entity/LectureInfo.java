package com.springboot.initialize_project.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="lecture_info")
public class LectureInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "senior")
    private User senior;

    @Column(nullable = false)
    private Boolean isCarat;

    @Column(nullable = false)
    private Boolean isComplete;

}