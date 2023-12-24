package com.hangout.core.hangoutpostsservice.entities;

import java.sql.Timestamp;
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
    @Column(name = "commentid")
    private UUID commentId;
    @ManyToOne
    @JoinColumn(name = "postid", referencedColumnName = "postId")
    private Post post;
    @JsonProperty(access = Access.READ_ONLY)
    @Column(name = "userid")
    private UUID userId;
    @Column(length = 500)
    private String text;
    @Column(name = "toplevel")
    private Boolean topLevel;
    @JsonProperty(access = Access.READ_ONLY)
    @Column(name = "createdat")
    private final Timestamp createdAt = Timestamp.from(Instant.now());
    @JsonProperty(access = Access.READ_ONLY)
    private final Integer replies = 0;
}