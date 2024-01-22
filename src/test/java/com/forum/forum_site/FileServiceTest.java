package com.forum.forum_site;

import com.forum.forum_site.service.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class FileServiceTest {

    @Autowired
    FileService fileService;

    // mock(모의) 파일 객페 생성 테스트
    private MockMultipartFile getMockUploadFile() throws IOException {
        return new MockMultipartFile("file", "file.jpg", "image/jpg", new FileInputStream("C:/Users/seongjae/OneDrive/사진/사촬감/KakaoTalk_20230330_225845259.jpg"));
    }

    @Test
    public void saveFileSuccess() throws Exception {
        String filePath = fileService.save(getMockUploadFile());

        // 파일 같은지 확인
        File file = new File(filePath);
        assertThat(file.exists()).isTrue();

        file.delete();//파일 삭제

    }

    @Test
    public void deleteFileSuccess() throws Exception {
        // 저장 후
        String filePath = fileService.save(getMockUploadFile());
        fileService.delete(filePath);

        // 삭제
        File file = new File(filePath);
        assertThat(file.exists()).isFalse();

    }
}
