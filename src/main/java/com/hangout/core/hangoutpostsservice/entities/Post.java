package com.hangout.core.hangoutpostsservice.entities;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID postId;
    private UUID pvId;
    private List<String> postMedias;
    @Column(length = 500)
    private String postDescription;
    @JsonProperty(access = Access.READ_ONLY)
    private Integer hearts;
    @JsonProperty(access = Access.READ_ONLY)
    private Integer comments;
    @JsonProperty(access = Access.READ_ONLY)
    private Integer interactions;
    @JsonProperty(access = Access.READ_ONLY)
    private final ZonedDateTime createdAt = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("Asia/Kolkata"));
}
