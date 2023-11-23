package com.hangout.core.hangoutpostsservice.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hangout.core.hangoutpostsservice.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, UUID> {

}
