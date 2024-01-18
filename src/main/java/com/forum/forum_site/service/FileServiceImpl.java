package com.forum.forum_site.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.processing.FilerException;

public class FileServiceImpl implements FileService{

    // todo - application.properties에 file.dir설정 하기

    @Value("{file.dir}")

    @Override
    public String save(MultipartFile multipartFile) throws FilerException {
        return null;
    }

    @Override
    public void delete(String filepath) {

    }
}
