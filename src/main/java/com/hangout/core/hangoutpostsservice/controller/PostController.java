package com.hangout.core.hangoutpostsservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hangout.core.hangoutpostsservice.entities.Post;
import com.hangout.core.hangoutpostsservice.services.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/post")
public class PostController {
    private final PostService postService;

    @PostMapping
    public String createPost(@RequestBody Post post) {
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
