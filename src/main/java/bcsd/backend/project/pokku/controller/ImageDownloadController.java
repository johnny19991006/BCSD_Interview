package bcsd.backend.project.pokku.controller;

import bcsd.backend.project.pokku.dto.Image.ImageDownloadRequest;
import bcsd.backend.project.pokku.dto.Image.ImageDownloadResponse;
import bcsd.backend.project.pokku.dto.Image.ImageUploadRequest;
import bcsd.backend.project.pokku.service.Image.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/download-img")
public class ImageDownloadController {

    private ImageServiceImpl imageService;

    @Autowired
    public void setImageService(ImageServiceImpl imageService){
        this.imageService = imageService;
    }

    @PostMapping("")
    public ResponseEntity<ImageDownloadResponse> download(@RequestBody ImageDownloadRequest request) throws Exception{
        return new ResponseEntity<>(imageService.download(request), HttpStatus.OK);
    }
}
