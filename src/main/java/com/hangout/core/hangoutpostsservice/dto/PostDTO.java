package com.hangout.core.hangoutpostsservice.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record PostDTO(String postDescription,MultipartFile files) {
}
