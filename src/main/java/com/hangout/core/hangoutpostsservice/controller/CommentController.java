package com.hangout.core.hangoutpostsservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hangout.core.hangoutpostsservice.dto.CommentDTO;
import com.hangout.core.hangoutpostsservice.dto.NewCommentRequest;
import com.hangout.core.hangoutpostsservice.dto.Reply;
import com.hangout.core.hangoutpostsservice.services.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public String createTopLevelComment(@RequestBody NewCommentRequest comment) {
        return commentService.createTopLevelComment(comment);
    }

    @PostMapping("/reply")
    public String createSubComment(@RequestBody Reply reply) {
        return commentService.createSubComments(reply);
    }

    @GetMapping("/all/{postId}")
    public List<CommentDTO> getAllTopLevelComments(@PathVariable String postId) {
        return commentService.fetchTopLevelCommentsForAPost(postId);
    }

    @GetMapping("/replies/{parentCommentId}")
    public List<CommentDTO> getAllChildCommentsOfAParentComment(@PathVariable String parentCommentId) {
        return commentService.fetchAllChildCommentsForAComment(parentCommentId);
    }
}
