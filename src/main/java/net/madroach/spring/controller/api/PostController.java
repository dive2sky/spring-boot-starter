package net.madroach.spring.controller.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.madroach.spring.model.Post;
import net.madroach.spring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sampark on 2016. 12. 17..
 */

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Post> getPosts(){
        return postService.getPosts();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public PostResponse putPost(
            @RequestBody Post post
    ){
        postService.putPost(post);

        return new PostResponse("OK");
    }

    @Data
    @AllArgsConstructor
    private class PostResponse{
         private String result;
    }




}
