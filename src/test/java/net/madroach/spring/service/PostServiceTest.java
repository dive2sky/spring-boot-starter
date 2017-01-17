package net.madroach.spring.service;

import lombok.extern.slf4j.Slf4j;
import net.madroach.spring.model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by sampark on 2017. 1. 17..
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    public void getPostTest(){
        List<Post> posts = postService.getPosts();
        log.info("posts:{}", posts);
    }
}
