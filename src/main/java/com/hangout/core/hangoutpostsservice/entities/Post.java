package com.hangout.core.hangoutpostsservice.entities;

import java.sql.Timestamp;
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
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "postid")
    private UUID postId;
    @Column(name = "pvid")
    private UUID pvId;
    @Column(name = "postmedias")
    private List<String> postMedias;
    @Column(name = "postdescription", length = 500)
    private String postDescription;
    @JsonProperty(access = Access.READ_ONLY)
    private Integer hearts;
    @JsonProperty(access = Access.READ_ONLY)
    private Integer comments;
    @JsonProperty(access = Access.READ_ONLY)
    private Integer interactions;
    @JsonProperty(access = Access.READ_ONLY)
    @Column(name = "createdat")
    private final Timestamp createdAt = Timestamp.from(Instant.now());
}
