package com.hangout.core.post.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hangout.core.post.entities.Media;
import com.hangout.core.post.entities.Post;
import com.hangout.core.post.projections.UploadedMedias;

public interface PostRepo extends JpaRepository<Post, UUID> {
    @Modifying
    @Query(value = "UPDATE post SET comments = comments+1, interactions = interactions+1 where post_id = :postid", nativeQuery = true)
    void increaseCommentCount(@Param("postid") UUID posUuid);

    @Modifying
    @Query(value = "UPDATE post SET hearts = hearts+1, interactions = interactions+1 where post_id = :postid", nativeQuery = true)
    void increaseHeartCount(@Param("postid") UUID posUuid);

    @Modifying
    @Query(value = "UPDATE post SET interactions = interactions+1 where post_id = :postid", nativeQuery = true)
    void increaseInteractionCount(@Param("postid") UUID posUuid);

    @Query(value = "SELECT post_medias from post where post_id = :postid", nativeQuery = true)
    UploadedMedias fetchUploadedMedias(@Param("postid") UUID posUuid);

    @Modifying
    @Query(value = "UPDATE post SET post_medias = :postmedias where post_id = :postid", nativeQuery = true)
    void replaceMedias(@Param("postmedias") List<Media> postMedias, @Param("postid") UUID posUuid);

    @Modifying
    @Query(value = "UPDATE post SET publish = true where post_id = :postid", nativeQuery = true)
    void publish(@Param("postid") UUID posUuid);
}
