package com.hangout.core.post_service.projections;

import java.util.List;

import com.hangout.core.post_service.entities.Media;

public interface UploadedMedias {
    List<Media> getMedias();
}
