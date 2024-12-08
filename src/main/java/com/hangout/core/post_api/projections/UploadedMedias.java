package com.hangout.core.post_api.projections;

import java.util.List;

import com.hangout.core.post_api.entities.Media;

public interface UploadedMedias {
    List<Media> getMedias();
}
