package com.forum.forum_site.dto;

import com.forum.forum_site.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
public class SavePostDto {
    @NotNull(message = "제목")
    private String title;

    @NotNull(message = "내용")
    private String content;

    private List<MultipartFile> uploadFiles;

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }

}
