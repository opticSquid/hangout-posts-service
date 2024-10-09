package com.hangout.core.post.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hangout.core.post.dto.PostCreationResponse;
import com.hangout.core.post.entities.Post;
import com.hangout.core.post.services.PostService;

import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/post")
public class PostController {
    private final PostService postService;

    @Observed(name = "create-post", contextualName = "controller", lowCardinalityKeyValues = { "postType",
            "MEDIA+TEXT" })
    @PostMapping(path = "/full", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostCreationResponse> createPostWithMediasAndText(
            @RequestHeader(name = "Authorization") String authToken,
            @RequestPart(value = "files") List<MultipartFile> files,
            @RequestPart(value = "postDescription") String postDescription) {
        return new ResponseEntity<>(postService.create(authToken, files, Optional.of(postDescription)),
                HttpStatus.CREATED);
    }

    @Observed(name = "create-post", contextualName = "controller", lowCardinalityKeyValues = { "postType", "MEDIA" })
    @PostMapping(path = "/short", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostCreationResponse> createPostWithMedias(
            @RequestHeader(name = "Authorization") String authToken,
            @RequestPart(value = "files") List<MultipartFile> files) {
        return new ResponseEntity<>(postService.create(authToken, files, Optional.empty()), HttpStatus.CREATED);
    }

    @Observed(name = "get-all-posts", contextualName = "controller")
    @GetMapping("/all")
    public List<Post> getAllPosts() {
        return postService.findAll();
    }

    @Observed(name = "get-particular-post", contextualName = "controller")
    @GetMapping("/{postId}")
    public Post getAParticularPost(@PathVariable String postId) {
        return postService.getParticularPost(postId);
    }
}
