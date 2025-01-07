package com.ll.jpa2501.global.init;

import com.ll.jpa2501.domain.post.Post;
import com.ll.jpa2501.domain.post.PostService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitData {
    @Bean
    public ApplicationRunner applicationRunner(PostService postService) {
        return args -> {
            Post post = postService.create("제목", "내용", "작성자");
        };
    }
}