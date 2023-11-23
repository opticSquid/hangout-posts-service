package com.hangout.core.hangoutpostsservice.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hangout.core.dto.CommentDTO;
import com.hangout.core.dto.Reply;
import com.hangout.core.hangoutpostsservice.entities.Comment;
import com.hangout.core.hangoutpostsservice.entities.HierarchyKeeper;
import com.hangout.core.hangoutpostsservice.entities.Post;
import com.hangout.core.hangoutpostsservice.repositories.CommentRepo;
import com.hangout.core.hangoutpostsservice.repositories.HierarchyKeeperRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepo commentRepo;
    private final HierarchyKeeperRepo hkRepo;
    private final PostService postService;

    private Comment saveComment(Comment comment) {
        return commentRepo.save(comment);
    }

    @Transactional
    public String createTopLevelComment(CommentDTO comment) {
        Post post = postService.getParticularPost(comment.postId());
        if (post != null) {
            Comment topLevelComment = new Comment();
            topLevelComment.setTopLevel(true);
            topLevelComment.setPost(post);
            topLevelComment.setText(comment.comment());
            topLevelComment = saveComment(topLevelComment);
            HierarchyKeeper hierarchy = new HierarchyKeeper();
            hierarchy.setParentComment(topLevelComment);
            hierarchy.setChildCommnet(topLevelComment);
            hkRepo.save(hierarchy);
            return topLevelComment.getCommentId().toString();
        } else {
            return null;
        }
    }

    @Transactional
    public String createSubComments(Reply reply) {
        Optional<Comment> maybeParentComment = commentRepo.findById(UUID.fromString(reply.parentCommentId()));
        if (maybeParentComment.isPresent()) {
            Comment parentComment = maybeParentComment.get();
            Comment childComment = new Comment();
            childComment.setTopLevel(false);
            childComment.setText(reply.comment());
            Post post = postService.getParticularPost(parentComment.getPost().getPostId().toString());
            childComment.setPost(post);
            childComment = saveComment(childComment);
            HierarchyKeeper hierarchy = new HierarchyKeeper();
            hierarchy.setParentComment(parentComment);
            hierarchy.setChildCommnet(childComment);
            hkRepo.save(hierarchy);
            return childComment.getCommentId().toString();
        } else {
            return null;
        }
    }
}
