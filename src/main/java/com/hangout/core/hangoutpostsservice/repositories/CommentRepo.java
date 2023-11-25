package com.hangout.core.hangoutpostsservice.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hangout.core.hangoutpostsservice.dto.FetchCommentProjection;
import com.hangout.core.hangoutpostsservice.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, UUID> {
    @Query(value = "SELECT commentid, createdat, text, userid FROM comment where postid=:postid AND toplevel=true;  ", nativeQuery = true)
    List<FetchCommentProjection> fetchTopLevelComments(@Param("postid") UUID postid);
}
