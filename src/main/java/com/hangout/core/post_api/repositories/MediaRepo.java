package com.hangout.core.post_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hangout.core.post_api.entities.Media;

public interface MediaRepo extends JpaRepository<Media, String> {

}