package com.hangout.core.post.dto;

import org.springframework.web.multipart.MultipartFile;

public record PostDTO(String postDescription, MultipartFile files) {
}
