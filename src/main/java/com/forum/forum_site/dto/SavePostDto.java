package com.forum.forum_site.dto;

import com.forum.forum_site.domain.Post;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;


public record SavePostDto(@NotBlank(message = "제목을 입력해주세요") String title,
                          @NotBlank(message = "내용을 입력해주세요") String content,
                          List<MultipartFile> uploadFile) {

    public Post toEntity() {
        return Post.builder().title(title).content(content).build();
    }
}