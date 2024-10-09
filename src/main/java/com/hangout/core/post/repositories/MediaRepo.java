package com.hangout.core.post.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hangout.core.post.entities.Media;

public interface MediaRepo extends JpaRepository<Media, String> {

}