package com.hangout.core.post_service.projections;

import java.time.Instant;
import java.util.UUID;

public interface FetchCommentProjection {
    UUID getCommentid();

    Instant getCreatedat();

    String getText();

    UUID getUserid();

}
