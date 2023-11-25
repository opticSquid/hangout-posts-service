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
        post.setComments(0);
        post.setHearts(0);
        post.setInteractions(0);
        Post savedPost = postRepo.save(post);
        return savedPost.getPostId().toString();
    }

    public List<Post> findAll() {
        return postRepo.findAll();
    }

    public Post getParticularPost(String postId) {
        Optional<Post> maybepost = postRepo.findById(UUID.fromString(postId));
        if (maybepost.isPresent()) {
            postRepo.increaseInteractionCount(UUID.fromString(postId));
            return maybepost.get();
        } else {
            return null;
        }
    }

    public void increaseCommentCount(UUID postId) {
        postRepo.increaseCommentCount(postId);
    }

    public void increaseHeartCount(UUID postId) {
        postRepo.increaseHeartCount(postId);
    }
}
