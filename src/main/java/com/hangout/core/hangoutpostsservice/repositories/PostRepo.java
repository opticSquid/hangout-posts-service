package com.hangout.core.hangoutpostsservice.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hangout.core.hangoutpostsservice.entities.Post;

public interface PostRepo extends JpaRepository<Post, UUID> {
    @Modifying
    @Query(value = "UPDATE post SET comments = comments+1, interactions = interactions+1 where postid=:postid", nativeQuery = true)
    void increaseCommentCount(@Param("postid") UUID posUuid);

    @Modifying
    @Query(value = "UPDATE post SET hearts = hearts+1, interactions = interactions+1 where postid=:postid", nativeQuery = true)
    void increaseHeartCount(@Param("postid") UUID posUuid);
}
