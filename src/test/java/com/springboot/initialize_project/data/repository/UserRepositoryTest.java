package com.springboot.initialize_project.data.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    /*void relationshipTest1(){
        //테스트 데이터 생성

        User user = new User();
        user.setAccount("testing");
        user.setCrn("testing");
        user.setNickname("testing");
        user.setPassword("12345");
        user.setPhone("01010101010");

        Post post = new Post();
        post.set_payable(true);
        post.setAnswer("test");
        post.setAvailable_day("mon");
        post.setContact_time(9);
        post.setDetail("testing");
        post.setLocation("testing");
        post.setPid(1L);
        post.setQuestion("testing");
        post.setSubject("testing");
        post.setUid(1L);

        System.out.println(
                "post : " + postRepository.findById(2L)
                        .orElseThrow(RuntimeException::new));

        System.out.println("user :" + userRepository.findById(2L)
                .orElseThrow(RuntimeException::new).getUsername());



    }*/

}
