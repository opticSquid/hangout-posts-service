package com.hangout.core.hangoutpostsservice.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hangout.core.hangoutpostsservice.entities.Post;

public interface PostRepo extends JpaRepository<Post, UUID> {

}
