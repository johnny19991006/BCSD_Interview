package com.forum.forum_site.service.file;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.processing.FilerException;

public interface FileService {
    // 저장된 파일 경로 반환
    String save(MultipartFile multipartFile) throws FilerException;

    void delete(String filepath);
}
