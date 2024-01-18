package com.forum.forum_site.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
public class UpdatePostDto {
    // Optional -> 다음 정보가 주어질 수도 있고 안 주어질 수도 있음
    private Optional<String> title;
    private Optional<String> content;
    private List<MultipartFile> uploadFiles;
}
