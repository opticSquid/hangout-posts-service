package com.hangout.core.hangoutpostsservice.entities;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID commentId;
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "postId")
    private Post post;
    @JsonProperty(access = Access.READ_ONLY)
    private UUID userId;
    @Column(length = 500)
    private String text;
    private Boolean topLevel;
    @JsonProperty(access = Access.READ_ONLY)
    private final ZonedDateTime createdAt = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("Asia/Kolkata"));
}