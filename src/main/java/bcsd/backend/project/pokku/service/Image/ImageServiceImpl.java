package bcsd.backend.project.pokku.service.Image;

import bcsd.backend.project.pokku.dao.ImageRepository;
import bcsd.backend.project.pokku.domain.Image;
import bcsd.backend.project.pokku.dto.Image.ImageDownloadRequest;
import bcsd.backend.project.pokku.dto.Image.ImageDownloadResponse;
import bcsd.backend.project.pokku.dto.Image.ImageUploadRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public Boolean upload(ImageUploadRequest request) throws Exception {

        try{
            String absolutePath = new File("").getAbsolutePath() + "\\";
            String path = "images/" + request.getName();
            File folder = new File("images/");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            if (!request.getImage().isEmpty()) {
                String contentType = request.getImage().getContentType();
                String originalFileExtension;
                if (ObjectUtils.isEmpty(contentType)) {
                    return false;
                } else {
                    if (contentType.contains("image/jpeg")) {
                        originalFileExtension = ".jpg";
                    } else if (contentType.contains("image/png")) {
                        originalFileExtension = ".png";
                    } else if (contentType.contains("image/gif")) {
                        originalFileExtension = ".gif";
                    }
                    else {
                        return false;
                    }
                }
                File destination = new File(absolutePath + File.separator + path + originalFileExtension);
                request.getImage().transferTo(destination);
                imageRepository.save(Image.builder()
                            .imageName(request.getName())
                            .imageUrl(path + originalFileExtension)
                            .build());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

//    @Override
//    public ImageDownloadResponse download(ImageDownloadRequest request) throws Exception{
//
//    }

}
