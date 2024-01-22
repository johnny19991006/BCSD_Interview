package bcsd.backend.project.pokku.service.Image;

import bcsd.backend.project.pokku.dto.Image.ImageDownloadRequest;
import bcsd.backend.project.pokku.dto.Image.ImageDownloadResponse;
import bcsd.backend.project.pokku.dto.Image.ImageUploadRequest;

public interface ImageService {
    public Boolean upload(ImageUploadRequest request) throws Exception;
    public ImageDownloadResponse download(ImageDownloadRequest request) throws Exception;
    public Boolean deleteImage(String imageName) throws Exception;
}
