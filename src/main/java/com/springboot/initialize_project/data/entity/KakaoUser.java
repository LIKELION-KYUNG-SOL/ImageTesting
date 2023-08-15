package com.springboot.initialize_project.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="kakao_user")
public class KakaoUser extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long kid;

    @Column(nullable = false, unique = true)
    private String kakaoEmail;

}