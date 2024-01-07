package com.hangout.core.hangoutpostsservice.services;

import com.hangout.core.hangoutpostsservice.dto.FileUploadRequestDTO;
import com.hangout.core.hangoutpostsservice.dto.FileUploadResponseDTO;
import com.hangout.core.hangoutpostsservice.dto.PostDTO;
import com.hangout.core.hangoutpostsservice.entities.Post;
import com.hangout.core.hangoutpostsservice.exceptions.FileUploadFailed;
import com.hangout.core.hangoutpostsservice.repositories.PostRepo;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepo postRepo;
    private final RestClient rc = RestClient.create();

    @Observed(name = "create-post", contextualName = "controller function ===> create post service function(with DB call)")
    public String create(PostDTO postDto) {
        String fileNames = uploadFiles(postDto.files());
        Post newPost = new Post(fileNames, postDto.postDescription());
        Post savedPost = postRepo.save(newPost);
        return savedPost.getPostId().toString();
    }

    @Observed(name = "create-post", contextualName = "create post service function(with DB call) ===> upload request to storage service")
    public String uploadFiles(MultipartFile files) {
        try {
            FileUploadRequestDTO request = new FileUploadRequestDTO(files);
            ResponseEntity<FileUploadResponseDTO> uploadResponse = rc.post().uri("http://localhost:8080/upload").contentType(request.getContentType()).body(request.getBody()).retrieve().toEntity(FileUploadResponseDTO.class);
            if (uploadResponse.getStatusCode().is2xxSuccessful()) {
                return Objects.requireNonNull(uploadResponse.getBody()).uploadStatus();
            } else {
                throw new FileUploadFailed("Storage Engine returned error response");
            }
        } catch (IOException e) {
            throw new FileUploadFailed("could not retrieve file size of the attached file");
        }
    }

    public List<Post> findAll() {
        return postRepo.findAll();
    }

    public Post getParticularPost(String postId) {
        Optional<Post> maybepost = postRepo.findById(UUID.fromString(postId));
        if (maybepost.isPresent()) {
            postRepo.increaseInteractionCount(UUID.fromString(postId));
            return maybepost.get();
        } else {
            return null;
        }
    }

    public void increaseCommentCount(UUID postId) {
        postRepo.increaseCommentCount(postId);
    }

    public void increaseHeartCount(UUID postId) {
        postRepo.increaseHeartCount(postId);
    }
}
