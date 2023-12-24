package com.hangout.core.hangoutpostsservice.dto;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.UUID;

public interface FetchCommentProjection {
    UUID getCommentid();

    Instant getCreatedat();

    String getText();

    UUID getUserid();

}
