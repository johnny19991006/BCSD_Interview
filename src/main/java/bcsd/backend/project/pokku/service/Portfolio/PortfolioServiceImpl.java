package bcsd.backend.project.pokku.service.Portfolio;

import bcsd.backend.project.pokku.dao.*;
import bcsd.backend.project.pokku.domain.*;
import bcsd.backend.project.pokku.dto.Image.ImageDownloadResponse;
import bcsd.backend.project.pokku.dto.PortfolioAbout.PortfolioAboutRequest;
import bcsd.backend.project.pokku.dto.PortfolioAbout.PortfolioAboutResponse;
import bcsd.backend.project.pokku.dto.PortfolioArchiving.PortfolioArchivingRequest;
import bcsd.backend.project.pokku.dto.PortfolioArchiving.PortfolioArchivingResponse;
import bcsd.backend.project.pokku.dto.PortfolioCareer.PortfolioCareerRequest;
import bcsd.backend.project.pokku.dto.PortfolioCareer.PortfolioCareerResponse;
import bcsd.backend.project.pokku.dto.PortfolioProject.PortfolioProjectRequest;
import bcsd.backend.project.pokku.dto.PortfolioProject.PortfolioProjectResponse;
import bcsd.backend.project.pokku.dto.PortfolioSkills.PortfolioSkillsListResponse;
import bcsd.backend.project.pokku.dto.PortfolioSkills.PortfolioSkillsRequest;
import bcsd.backend.project.pokku.dto.PortfolioSkills.PortfolioSkillsResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final UserPortfolioSkillsFrontendRepository userPortfolioSkillsFrontendRepository;
    private final SkillsFrontendRepository skillsFrontendRepository;

    private final UserPortfolioSkillsBackendRepository userPortfolioSkillsBackendRepository;
    private final SkillsBackendRepository skillsBackendRepository;

    private final UserPortfolioSkillsDeploymentRepository userPortfolioSkillsDeploymentRepository;
    private final SkillsDeploymentRepository skillsDeploymentRepository;

    private final UserPortfolioSkillsMobileappRepository userPortfolioSkillsMobileappRepository;
    private final SkillsMobileappRepository skillsMobileappRepository;

    private final UserPortfolioSkillsVersioncontrolRepository userPortfolioSkillsVersioncontrolRepository;
    private final SkillsVersioncontrolRepository skillsVersioncontrolRepository;

    private final UserPortfolioSkillsCommunicationRepository userPortfolioSkillsCommunicationRepository;
    private final SkillsCommunicationRepository skillsCommunicationRepository;

    private final UserPortfolioSkillsCertificationRepository userPortfolioSkillsCertificationRepository;
    private final SkillsCertificationRepository skillsCertificationRepository;

    private final PortfolioAboutRepository portfolioAboutRepository;

    private final PortfolioArchivingRepository portfolioArchivingRepository;

    private final PortfolioCareerRepository portfolioCareerRepository;

    private final PortfolioProjectRepository portfolioProjectRepository;

    private final ImageRepository imageRepository;


    @Override
    public PortfolioAboutResponse findPortfolioAbout(String userId) throws Exception {
        PortfolioAbout portfolioAbout = portfolioAboutRepository.findByUserId(UserInfo.builder().userId(userId).build())
                .orElseThrow(() -> new BadCredentialsException("잘못된 계정 정보 입니다."));

        return new PortfolioAboutResponse(portfolioAbout);
    }

    @Override
    public Boolean updatePortfolioAbout(String userId, PortfolioAboutRequest request) throws Exception {
        try {
            portfolioAboutRepository.updateById(UserInfo.builder()
                            .userId(userId)
                            .build(),
                    request.getUserEducationVisible(),
                    request.getUserEmailVisible(),
                    request.getUserNameVisible(),
                    request.getUserTelVisible());
        } catch (Exception e) {

//            System.out.println(e.getMessage());
//            throw new Exception("Wrong" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public PortfolioSkillsResponse findSkills(String userId) throws Exception {
        PortfolioSkillsResponse response = new PortfolioSkillsResponse();
        response.setSkillsFrontendIdList(userPortfolioSkillsFrontendRepository.findByUserId(UserInfo.builder().userId(userId).build()));
        response.setSkillsBackendIdList(userPortfolioSkillsBackendRepository.findByUserId(UserInfo.builder().userId(userId).build()));
        response.setSkillsMobileappIdList(userPortfolioSkillsMobileappRepository.findByUserId(UserInfo.builder().userId(userId).build()));
        response.setSkillsDeploymentIdList(userPortfolioSkillsDeploymentRepository.findByUserId(UserInfo.builder().userId(userId).build()));
        response.setSkillsVersioncontrolIdList(userPortfolioSkillsVersioncontrolRepository.findByUserId(UserInfo.builder().userId(userId).build()));
        response.setSkillsCommunicationIdList(userPortfolioSkillsCommunicationRepository.findByUserId(UserInfo.builder().userId(userId).build()));
        response.setSkillsCertificationIdList(userPortfolioSkillsCertificationRepository.findByUserId(UserInfo.builder().userId(userId).build()));
        return response;
    }

    @Override
    public Boolean addSkills(String userId, PortfolioSkillsRequest request) throws Exception {
        if (request.getCategory().equals("frontend")){
            skillsFrontendRepository.findById(request.getSkillsId())
                    .orElseThrow(() -> new Exception("존재하지 않는 skills_frontend_id 입니다."));

            Optional<Long> exists = userPortfolioSkillsFrontendRepository.findEntityCnt(
                    UserInfo.builder().userId(userId).build(),
                    SkillsFrontend.builder().skillsFontendId(request.getSkillsId()).build());

            if(exists.get() == 0) {
                userPortfolioSkillsFrontendRepository.save(UserPortfolioSkillsFrontend.builder()
                        .skillsFrontend(SkillsFrontend.builder().skillsFontendId(request.getSkillsId()).build())
                        .userInfo(UserInfo.builder().userId(userId).build())
                        .build());
            }else{
                return false;
            }
        }else if(request.getCategory().equals("backend")){
            skillsBackendRepository.findById(request.getSkillsId())
                    .orElseThrow(() -> new Exception("존재하지 않는 skills_backend_id 입니다."));

            Optional<Long> exists = userPortfolioSkillsBackendRepository.findEntityCnt(
                    UserInfo.builder().userId(userId).build(),
                    SkillsBackend.builder().skillsBackendId(request.getSkillsId()).build());

            if(exists.get() == 0) {
                userPortfolioSkillsBackendRepository.save(UserPortfolioSkillsBackend.builder()
                        .skillsBackend(SkillsBackend.builder().skillsBackendId(request.getSkillsId()).build())
                        .userInfo(UserInfo.builder().userId(userId).build())
                        .build());
            }else{
                return false;
            }
        }else if(request.getCategory().equals("deployment")){
            skillsDeploymentRepository.findById(request.getSkillsId())
                    .orElseThrow(() -> new Exception("존재하지 않는 skills_deployment_id 입니다."));

            Optional<Long> exists = userPortfolioSkillsDeploymentRepository.findEntityCnt(
                    UserInfo.builder().userId(userId).build(),
                    SkillsDeployment.builder().skillsDeploymentId(request.getSkillsId()).build());

            if(exists.get() == 0) {
                userPortfolioSkillsDeploymentRepository.save(UserPortfolioSkillsDeployment.builder()
                        .skillsDeployment(SkillsDeployment.builder().skillsDeploymentId(request.getSkillsId()).build())
                        .userInfo(UserInfo.builder().userId(userId).build())
                        .build());
            }else{
                return false;
            }
        }else if(request.getCategory().equals("mobileapp")){
            skillsMobileappRepository.findById(request.getSkillsId())
                    .orElseThrow(() -> new Exception("존재하지 않는 skills_mobileapp_id 입니다."));

            Optional<Long> exists = userPortfolioSkillsMobileappRepository.findEntityCnt(
                    UserInfo.builder().userId(userId).build(),
                    SkillsMobileapp.builder().skillsMobileappId(request.getSkillsId()).build());

            if(exists.get() == 0) {
                userPortfolioSkillsMobileappRepository.save(UserPortfolioSkillsMobileapp.builder()
                        .skillsMobileapp(SkillsMobileapp.builder().skillsMobileappId(request.getSkillsId()).build())
                        .userInfo(UserInfo.builder().userId(userId).build())
                        .build());
            }else{
                return false;
            }
        }else if(request.getCategory().equals("versioncontrol")){
            skillsVersioncontrolRepository.findById(request.getSkillsId())
                    .orElseThrow(() -> new Exception("존재하지 않는 skills_versioncontrol_id 입니다."));

            Optional<Long> exists = userPortfolioSkillsVersioncontrolRepository.findEntityCnt(
                    UserInfo.builder().userId(userId).build(),
                    SkillsVersioncontrol.builder().skillsVersioncontrolId(request.getSkillsId()).build());

            if(exists.get() == 0) {
                userPortfolioSkillsVersioncontrolRepository.save(UserPortfolioSkillsVersioncontrol.builder()
                        .skillsVersioncontrol(SkillsVersioncontrol.builder().skillsVersioncontrolId(request.getSkillsId()).build())
                        .userInfo(UserInfo.builder().userId(userId).build())
                        .build());
            }else{
                return false;
            }
        }else if(request.getCategory().equals("communication")){
            skillsCommunicationRepository.findById(request.getSkillsId())
                    .orElseThrow(() -> new Exception("존재하지 않는 skills_communication_id 입니다."));

            Optional<Long> exists = userPortfolioSkillsCommunicationRepository.findEntityCnt(
                    UserInfo.builder().userId(userId).build(),
                    SkillsCommunication.builder().skillsCommunicationId(request.getSkillsId()).build());

            if(exists.get() == 0) {
                userPortfolioSkillsCommunicationRepository.save(UserPortfolioSkillsCommunication.builder()
                        .skillsCommunication(SkillsCommunication.builder().skillsCommunicationId(request.getSkillsId()).build())
                        .userInfo(UserInfo.builder().userId(userId).build())
                        .build());
            }else{
                return false;
            }
        }else if(request.getCategory().equals("certification")){
            skillsCertificationRepository.findById(request.getSkillsId())
                    .orElseThrow(() -> new Exception("존재하지 않는 skills_communication_id 입니다."));

            Optional<Long> exists = userPortfolioSkillsCertificationRepository.findEntityCnt(
                    UserInfo.builder().userId(userId).build(),
                    SkillsCertification.builder().skillsCertificationId(request.getSkillsId()).build());

            if(exists.get() == 0) {
                userPortfolioSkillsCertificationRepository.save(UserPortfolioSkillsCertification.builder()
                        .skillsCertification(SkillsCertification.builder().skillsCertificationId(request.getSkillsId()).build())
                        .userInfo(UserInfo.builder().userId(userId).build())
                        .build());
            }else{
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean deleteSkills(String userId, PortfolioSkillsRequest request) throws Exception {
        if (request.getCategory().equals("frontend")) {
            UserPortfolioSkillsFrontend exists = userPortfolioSkillsFrontendRepository.findEntity(
                            UserInfo.builder().userId(userId).build(),
                            SkillsFrontend.builder().skillsFontendId(request.getSkillsId()).build())
                    .orElseThrow(() -> new BadCredentialsException("유효하지 않은 데이터 입니다."));

            userPortfolioSkillsFrontendRepository.deleteById(exists.getUserPortfolioSkillsFrontendId());

        }else if(request.getCategory().equals("backend")) {
            UserPortfolioSkillsBackend exists = userPortfolioSkillsBackendRepository.findEntity(
                            UserInfo.builder().userId(userId).build(),
                            SkillsBackend.builder().skillsBackendId(request.getSkillsId()).build())
                    .orElseThrow(() -> new BadCredentialsException("유효하지 않은 데이터 입니다."));

            userPortfolioSkillsBackendRepository.deleteById(exists.getUserPortfolioSkillsBackendId());

        }else if(request.getCategory().equals("deployment")) {
            UserPortfolioSkillsDeployment exists = userPortfolioSkillsDeploymentRepository.findEntity(
                            UserInfo.builder().userId(userId).build(),
                            SkillsDeployment.builder().skillsDeploymentId(request.getSkillsId()).build())
                    .orElseThrow(() -> new BadCredentialsException("유효하지 않은 데이터 입니다."));

            userPortfolioSkillsDeploymentRepository.deleteById(exists.getUserPortfolioSkillsDeploymentId());

        }else if(request.getCategory().equals("mobileapp")) {
            UserPortfolioSkillsMobileapp exists = userPortfolioSkillsMobileappRepository.findEntity(
                            UserInfo.builder().userId(userId).build(),
                            SkillsMobileapp.builder().skillsMobileappId(request.getSkillsId()).build())
                    .orElseThrow(() -> new BadCredentialsException("유효하지 않은 데이터 입니다."));

            userPortfolioSkillsMobileappRepository.deleteById(exists.getUserPortfolioSkillsMobileappId());

        }else if(request.getCategory().equals("versioncontrol")) {
            UserPortfolioSkillsVersioncontrol exists = userPortfolioSkillsVersioncontrolRepository.findEntity(
                            UserInfo.builder().userId(userId).build(),
                            SkillsVersioncontrol.builder().skillsVersioncontrolId(request.getSkillsId()).build())
                    .orElseThrow(() -> new BadCredentialsException("유효하지 않은 데이터 입니다."));

            userPortfolioSkillsVersioncontrolRepository.deleteById(exists.getUserPortfolioSkillsVersioncontrolId());

        }else if(request.getCategory().equals("communication")) {
            UserPortfolioSkillsCommunication exists = userPortfolioSkillsCommunicationRepository.findEntity(
                            UserInfo.builder().userId(userId).build(),
                            SkillsCommunication.builder().skillsCommunicationId(request.getSkillsId()).build())
                    .orElseThrow(() -> new BadCredentialsException("유효하지 않은 데이터 입니다."));

            userPortfolioSkillsCommunicationRepository.deleteById(exists.getUserPortfolioSkillsCommunicationId());

        }else if(request.getCategory().equals("certification")) {
            UserPortfolioSkillsCertification exists = userPortfolioSkillsCertificationRepository.findEntity(
                            UserInfo.builder().userId(userId).build(),
                            SkillsCertification.builder().skillsCertificationId(request.getSkillsId()).build())
                    .orElseThrow(() -> new BadCredentialsException("유효하지 않은 데이터 입니다."));

            userPortfolioSkillsCertificationRepository.deleteById(exists.getUserPortfolioSkillsCertificationId());

        }

        return true;
    }

    @Override
    public List<PortfolioSkillsListResponse> findSkillsList(String imageCategory) throws Exception {
        List<PortfolioSkillsListResponse> response = new ArrayList<>();
        if(imageCategory.equals("frontend")){
            List<SkillsFrontend> skillsFrontendList = skillsFrontendRepository.findAll();
            for(SkillsFrontend s: skillsFrontendList){
                response.add(new PortfolioSkillsListResponse(s.getSkillsFontendId(), s.getImage().getSkillName()));
            }
        }

        return response;
    }

    @Override
    public List<PortfolioArchivingResponse> findArchiving(String userId) throws Exception {
        List<PortfolioArchiving> portfolioArchivingList = portfolioArchivingRepository.findByUserId(UserInfo.builder().userId(userId).build())
                .orElseThrow(() -> new BadCredentialsException("false"));
        List<PortfolioArchivingResponse> response = new ArrayList<>();
        for (PortfolioArchiving p: portfolioArchivingList){
            response.add(new PortfolioArchivingResponse(p.getPortfolioArchivingId(), p.getArchivingName(), p.getArchivingExplanation()));
        }

        return response;
    }

    @Override
    public Boolean addArchiving(String userId, PortfolioArchivingRequest request) throws Exception {
        portfolioArchivingRepository.save(PortfolioArchiving.builder()
                .archivingExplanation(request.getArchivingExplanation())
                .archivingName(request.getArchivingName())
                .userInfo(UserInfo.builder().userId(userId).build())
                .build());
        return true;
    }

    @Override
    public Boolean updateArchiving(String userId, PortfolioArchivingRequest request) throws Exception {
        portfolioArchivingRepository.save(PortfolioArchiving.builder()
                .portfolioArchivingId(request.getPortfolioArchivingId())
                .archivingExplanation(request.getArchivingExplanation())
                .archivingName(request.getArchivingName())
                .userInfo(UserInfo.builder().userId(userId).build())
                .build());
        return true;
    }

    @Override
    public Boolean deleteArchiving(String userId, PortfolioArchivingRequest request) throws Exception {
        portfolioArchivingRepository.deleteByUserIdAndName(
                UserInfo.builder().userId(userId).build(),
                request.getArchivingName());
        return true;
    }

    @Override
    public List<PortfolioCareerResponse> findCareer(String userId) throws Exception {
        List<PortfolioCareer> portfolioCareerList = portfolioCareerRepository.findByUserId(UserInfo.builder().userId(userId).build())
                .orElseThrow(() -> new BadCredentialsException("false"));
        List<PortfolioCareerResponse> response = new ArrayList<>();
        for (PortfolioCareer p: portfolioCareerList){
            response.add(new PortfolioCareerResponse(p.getPortfolioCareerId(), p.getCareerExplanation()));
        }

        return response;
    }

    @Override
    public Boolean addCareer(String userId, PortfolioCareerRequest request) throws Exception {
        portfolioCareerRepository.save(PortfolioCareer.builder()
                .careerExplanation(request.getCareerExplanation())
                .userInfo(UserInfo.builder().userId(userId).build())
                .build());
        return true;
    }

    @Override
    public Boolean updateCareer(String userId, PortfolioCareerRequest request) throws Exception {
        portfolioCareerRepository.save(PortfolioCareer.builder()
                .portfolioCareerId(request.getPortfolioCareerId())
                .careerExplanation(request.getCareerExplanation())
                .userInfo(UserInfo.builder().userId(userId).build())
                .build());
        return true;
    }

    @Override
    public Boolean deleteCareer(String userId, PortfolioCareerRequest request) throws Exception {

        portfolioCareerRepository.deleteByUserIdAndExplanation(
                UserInfo.builder().userId(userId).build(),
                request.getCareerExplanation());

        return true;

    }

    @Override
    public List<PortfolioProjectResponse> findProject(String userId) throws Exception {
        List<PortfolioProject> portfolioProjectList = portfolioProjectRepository.findByUserId(UserInfo.builder().userId(userId).build())
                .orElseThrow(() -> new BadCredentialsException("false"));
        List<PortfolioProjectResponse> response = new ArrayList<>();
        for (PortfolioProject p: portfolioProjectList){
            response.add(new PortfolioProjectResponse(p.getPortfolioProjectId(), p.getProjectName(), p.getProjectExplanation()));
        }

        return response;
    }

    @Override
    public Boolean addProject(String userId, PortfolioProjectRequest request) throws Exception {
        portfolioProjectRepository.save(PortfolioProject.builder()
                .projectExplanation(request.getProjectExplanation())
                .projectName(request.getProjectName())
                .userInfo(UserInfo.builder().userId(userId).build())
                .build());
        return true;
    }

    @Override
    public Boolean updateProject(String userId, PortfolioProjectRequest request) throws Exception {
        portfolioProjectRepository.save(PortfolioProject.builder()
                .portfolioProjectId(request.getPortfolioProjectId())
                .projectExplanation(request.getProjectExplanation())
                .projectName(request.getProjectName())
                .userInfo(UserInfo.builder().userId(userId).build())
                .build());
        return true;
    }

    @Override
    public Boolean deleteProject(String userId, PortfolioProjectRequest request) throws Exception {
        portfolioProjectRepository.deleteByUserIdAndName(
                UserInfo.builder().userId(userId).build(),
                request.getProjectName());
        return true;
    }

    @Override
    public ImageDownloadResponse download(String imageName) throws Exception{
        Optional<Image> image = imageRepository.findById(imageName);
        ImageDownloadResponse response = new ImageDownloadResponse();
        byte[] imageBytes = Files.readAllBytes(Paths.get(new File("").getAbsolutePath() + File.separator + image.get().getImageUrl()));
        response.setBase64Images(Base64.getEncoder().encodeToString(imageBytes));
        return response;
    }
}
