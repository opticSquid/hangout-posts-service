package com.hangout.core.post_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hangout.core.post_service.entities.Media;

public interface MediaRepo extends JpaRepository<Media, String> {

}