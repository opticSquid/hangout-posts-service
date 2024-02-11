package com.hangout.core.hangoutpostsservice.controller;

import java.util.List;

import com.hangout.core.hangoutpostsservice.dto.PostDTO;
import io.micrometer.observation.annotation.Observed;
import org.springframework.web.bind.annotation.*;

import com.hangout.core.hangoutpostsservice.entities.Post;
import com.hangout.core.hangoutpostsservice.services.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
@Observed(name = "create-post", contextualName = "gateway ===> controller function")
    @PostMapping
    public String createPost(@ModelAttribute PostDTO post) {
        return postService.create(post);
    }

    @GetMapping("/all")
    public List<Post> getAllPosts() {
        return postService.findAll();
    }

    @GetMapping("/{postId}")
    public Post getAParticularPost(@PathVariable String postId) {
        return postService.getParticularPost(postId);
    }
}
