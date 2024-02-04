package com.forum.forum_site.dto.post;

import com.forum.forum_site.domain.Post;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

// record 클래스
// 데이터를 저장하고 표현 할때 불변 데이터 클래스를 생성하는데 사용 됨 -> 안정성 증가
// 생성자를 자동으로 생성해줌
public record SavePostDto(@NotBlank(message = "제목을 입력해주세요") String title,
                          @NotBlank(message = "내용을 입력해주세요") String content,
                          List<MultipartFile> uploadFile) {

    public Post toEntity() {
        return Post.builder().title(title).content(content).build();
    }
}