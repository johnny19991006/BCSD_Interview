package bcsd.backend.project.pokku.service.Image;

import bcsd.backend.project.pokku.dao.ImageRepository;
import bcsd.backend.project.pokku.dao.SkillsBackendRepository;
import bcsd.backend.project.pokku.dao.SkillsFrontendRepository;
import bcsd.backend.project.pokku.domain.Image;
import bcsd.backend.project.pokku.domain.SkillsBackend;
import bcsd.backend.project.pokku.domain.SkillsFrontend;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final SkillsFrontendRepository skillsFrontendRepository;
    private final SkillsBackendRepository skillsBackendRepository;

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

                Image img = Image.builder()
                        .skillName(request.getName())
                        .imageUrl(path + originalFileExtension)
                        .build();

                imageRepository.save(img);

                if(request.getCategory().equals("backend")){
                    skillsBackendRepository.save(SkillsBackend.builder()
                            .image(img)
                            .build());
                }else if(request.getCategory().equals("frontend")){
                    skillsFrontendRepository.save(SkillsFrontend.builder()
                            .image(img)
                            .build());
                }else if(request.getCategory().equals("mobileapp")){

                }else if(request.getCategory().equals("deployment")){

                }else if(request.getCategory().equals("versioncontrol")){

                }else if(request.getCategory().equals("communication")){

                }else if(request.getCategory().equals("certification")){

                }

                File destination = new File(absolutePath + File.separator + path + originalFileExtension);
                request.getImage().transferTo(destination);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public ImageDownloadResponse download(ImageDownloadRequest request) throws Exception{
        List<Image> images = imageRepository.findAllById(request.getImageName());
        List<String> res = new ArrayList<>();
        for (Image img: images) {
            byte[] imageBytes = Files.readAllBytes(Paths.get(new File("").getAbsolutePath() + File.separator + img.getImageUrl()));
            res.add(Base64.getEncoder().encodeToString(imageBytes));
        }
        return new ImageDownloadResponse(res);
    }

}
