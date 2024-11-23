package com.hangout.core.post_service.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hangout.core.post_service.entities.Comment;
import com.hangout.core.post_service.projections.FetchCommentProjection;

public interface CommentRepo extends JpaRepository<Comment, UUID> {
    @Query(value = "SELECT commentid, createdat, text, userid FROM comment where postid=:postid AND toplevel=true;  ", nativeQuery = true)
    List<FetchCommentProjection> fetchTopLevelComments(@Param("postid") UUID postid);
}
