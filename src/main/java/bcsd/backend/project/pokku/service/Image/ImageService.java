package bcsd.backend.project.pokku.service.Image;

import bcsd.backend.project.pokku.dto.Image.ImageUploadRequest;

public interface ImageService {
    public Boolean upload(ImageUploadRequest request) throws RuntimeException;
    public Boolean deleteImage(String imageName) throws RuntimeException;
}
