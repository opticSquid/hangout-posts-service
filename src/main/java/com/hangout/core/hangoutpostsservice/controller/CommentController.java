package com.hangout.core.hangoutpostsservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hangout.core.dto.CommentDTO;
import com.hangout.core.dto.Reply;
import com.hangout.core.hangoutpostsservice.services.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contents/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public String createTopLevelComment(@RequestBody CommentDTO comment) {
        return commentService.createTopLevelComment(comment);
    }

    @PostMapping("/reply")
    public String createSubComment(@RequestBody Reply reply) {
        return commentService.createSubComments(reply);
    }

}
