package com.springboot.initialize_project.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "post")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column(nullable = false)
    private String subject;
    @Column(nullable = false)
    private String thumbnail;


    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private int number;

    @Column(nullable = false)
    private String start_time;

    @Column(nullable = false)
    private String finish_time;

    @Column(nullable = false)
    private String available_day;

    @Column(nullable = false)
    private String detail;

    @Column(nullable = false)
    private boolean is_payable;

    @ManyToOne
    @JoinColumn(name = "uid")
    @ToString.Exclude
    private User lecturer;
}
