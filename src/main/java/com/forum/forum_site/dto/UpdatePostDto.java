package com.forum.forum_site.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public record UpdatePostDto (
        // Optional -> 다음 정보가 주어질 수도 있고 안 주어질 수도 있음
        Optional<String> title,
        Optional<String> content,
        List<MultipartFile> uploadFiles){

}
