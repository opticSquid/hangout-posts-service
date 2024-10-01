package com.hangout.core.post.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hangout.core.post.dto.FetchCommentProjection;
import com.hangout.core.post.entities.HierarchyKeeper;

public interface HierarchyKeeperRepo extends JpaRepository<HierarchyKeeper, Integer> {
    @Query(value = "SELECT c.commentid, c.createdat, c.text, c.userid FROM comment AS c JOIN hierarchy_keeper k ON c.commentid = k.childcommentid WHERE k.parentcommentid = :commentId", nativeQuery = true)
    List<FetchCommentProjection> findAllChildComments(@Param("commentId") UUID commentId);
}
