package bcsd.backend.project.pokku.service.Image;

import bcsd.backend.project.pokku.dao.*;
import bcsd.backend.project.pokku.domain.*;
import bcsd.backend.project.pokku.dto.Image.ImageUploadRequest;
import bcsd.backend.project.pokku.exception.DuplicateKeyException.DuplicateKeyException;
import bcsd.backend.project.pokku.exception.NoSuchDataException.NoSuchDataException;
import bcsd.backend.project.pokku.exception.NotSupportException.NotSupportException;
import bcsd.backend.project.pokku.exception.NullValueException.NullValueException;
import bcsd.backend.project.pokku.exception.ResCode;
import bcsd.backend.project.pokku.exception.UnknownException.UnknownException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.File;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final SkillsFrontendRepository skillsFrontendRepository;
    private final SkillsBackendRepository skillsBackendRepository;
    private final SkillsMobileappRepository skillsMobileappRepository;
    private final SkillsDeploymentRepository skillsDeploymentRepository;
    private final SkillsVersioncontrolRepository skillsVersioncontrolRepository;
    private final SkillsCommunicationRepository skillsCommunicationRepository;
    private final SkillsCertificationRepository skillsCertificationRepository;

    @Override
    public Boolean upload(ImageUploadRequest request) throws RuntimeException {

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
                    throw new NullValueException("contentType이 존재하지 않습니다.", contentType, ResCode.NULL_VALUE.value());
                } else {
                    if (contentType.contains("image/jpeg")) {
                        originalFileExtension = ".jpg";
                    } else if (contentType.contains("image/png")) {
                        originalFileExtension = ".png";
                    } else if (contentType.contains("image/gif")) {
                        originalFileExtension = ".gif";
                    }
                    else {
                        throw new NotSupportException("해당 contentType은 지원하지 않습니다.", contentType, ResCode.NOT_SUPPORT.value());
                    }
                }

                Image img = Image.builder()
                        .skillName(request.getName() + originalFileExtension)
                        .imageUrl(path + originalFileExtension)
                        .build();

                if (imageRepository.countByName(request.getName() + originalFileExtension) != 0){
                    throw new DuplicateKeyException("이미 존재하는 이미지 또는 이름 입니다.", request.getName() + originalFileExtension, ResCode.DUPLICATE_KEY.value());
                }

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
                    skillsMobileappRepository.save(SkillsMobileapp.builder()
                            .image(img)
                            .build());
                }else if(request.getCategory().equals("deployment")){
                    skillsDeploymentRepository.save(SkillsDeployment.builder()
                            .image(img)
                            .build());
                }else if(request.getCategory().equals("versioncontrol")){
                    skillsVersioncontrolRepository.save(SkillsVersioncontrol.builder()
                            .image(img)
                            .build());
                }else if(request.getCategory().equals("communication")){
                    skillsCommunicationRepository.save(SkillsCommunication.builder()
                            .image(img)
                            .build());
                }else if(request.getCategory().equals("certification")){
                    skillsCertificationRepository.save(SkillsCertification.builder()
                            .image(img)
                            .build());
                }

                File destination = new File(absolutePath + File.separator + path + originalFileExtension);
                request.getImage().transferTo(destination);

            }

        } catch (Exception e) {
            throw new UnknownException(e.getMessage(), "unknown", ResCode.UNKNOWN.value());
        }
        return true;
    }

    @Override
    public Boolean deleteImage(String imageName) throws RuntimeException{
        try {
            // 이미지 파일의 절대 경로를 생성
            String absolutePath = new File("").getAbsolutePath() + "\\";
            String path = absolutePath + "images/" + imageName;

            // File 객체를 생성하여 이미지 파일을 삭제
            File imageFile = new File(path);
            if (imageFile.exists()) {
                if (imageFile.delete()) {
                    imageRepository.deleteById(imageName);
                    return true;
                } else {
                    throw new NoSuchDataException("해당 이미지 정보가 존재하지 않습니다.", imageName, ResCode.NO_SUCH_DATA.value());
                }
            } else {
                throw new NoSuchDataException("해당 이미지가 존재하지 않습니다.", path, ResCode.NO_SUCH_DATA.value());
            }
        } catch (Exception e) {
            throw new UnknownException(e.getMessage(), "unknown", ResCode.UNKNOWN.value());
        }
    }

}
