package com.hangout.core.post.dto;

import java.time.Instant;
import java.util.UUID;

public interface FetchCommentProjection {
    UUID getCommentid();

    Instant getCreatedat();

    String getText();

    UUID getUserid();

}
