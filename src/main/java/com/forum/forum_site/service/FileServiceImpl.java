package com.forum.forum_site.service;

import com.forum.forum_site.exception.FileException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileServiceImpl implements FileService{

    // application.properties 파일에 있는 file.dir의 내용을 가져옴
    @Value("{file.dir}")
    private String fileDir;

    @Override
    public String save(MultipartFile multipartFile) {
        String filePath = fileDir + UUID.randomUUID();
        try {
            multipartFile.transferTo(new File(filePath));
        }catch (IOException e) {
            //파일 저장 에러
            throw new FileException(FileException.Type.FILE_CAN_NOT_SAVE);
        }
        return filePath;
    }

    @Override
    public void delete(String filePath) {
        File file = new File(filePath);

        // 존재하지 않는 경우, 작업을 종료
        if (!file.exists()) return;

        if (!file.delete()) {
            throw new FileException(FileException.Type.FILE_CAN_NOT_DELETE);
        }
    }
}
