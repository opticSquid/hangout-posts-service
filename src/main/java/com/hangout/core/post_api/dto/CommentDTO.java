package com.hangout.core.post_api.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record CommentDTO(UUID commentId, Timestamp createdAt, String text, UUID userId) {

}
