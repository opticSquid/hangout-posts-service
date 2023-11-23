package com.hangout.core.hangoutpostsservice.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hangout.core.hangoutpostsservice.entities.Post;
import com.hangout.core.hangoutpostsservice.repositories.PostRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepo postRepo;

    public String create(Post post) {
        Post savedPost = postRepo.save(post);
        return savedPost.getPostId().toString();
    }

    public List<Post> findAll() {
        return postRepo.findAll();
    }

    public Post getParticularPost(String postId) {
        Optional<Post> maybepost = postRepo.findById(UUID.fromString(postId));
        return maybepost.orElse(null);
    }
}
