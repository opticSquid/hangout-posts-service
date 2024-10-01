package com.hangout.core.post.dto;

import lombok.Getter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Getter
public class FileUploadRequestDTO {
    private final MediaType contentType;
    private final long contentLength;
    private final MultiValueMap<String, Object> body;

    public FileUploadRequestDTO(MultipartFile multipartFile) throws IOException {
        Resource imageFile = new ByteArrayResource(multipartFile.getBytes()) {
            @Override
            public String getFilename() {
                return multipartFile.getOriginalFilename();
            }
        };
        this.contentType = MediaType.MULTIPART_FORM_DATA;
        this.contentLength = multipartFile.getSize();
        this.body = new LinkedMultiValueMap<>();
        body.add("file", imageFile);
    }

}
