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
import bcsd.backend.project.pokku.exception.DuplicateKeyException.DuplicateKeyException;
import bcsd.backend.project.pokku.exception.NoSuchDataException.NoSuchDataException;
import bcsd.backend.project.pokku.exception.ResCode;
import bcsd.backend.project.pokku.exception.UnknownException.UnknownException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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

    private final UserInfoRepository userInfoRepository;


    @Override
    public PortfolioAboutResponse findPortfolioAbout(String userId) throws RuntimeException {

        PortfolioAbout portfolioAbout = portfolioAboutRepository.findByUserId(UserInfo.builder().userId(userId).build())
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));

        return new PortfolioAboutResponse(portfolioAbout);
    }

    @Override
    public Boolean updatePortfolioAbout(String userId, PortfolioAboutRequest request) throws RuntimeException {

        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));

        portfolioAboutRepository.updateById(UserInfo.builder()
                        .userId(userId)
                        .build(),
                request.getUserEducationVisible(),
                request.getUserEmailVisible(),
                request.getUserNameVisible(),
                request.getUserTelVisible());

        return true;
    }

    @Override
    public PortfolioSkillsResponse findSkills(String userId) throws RuntimeException {
        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));

        PortfolioSkillsResponse response = new PortfolioSkillsResponse();

        List<UserPortfolioSkillsFrontend> userPortfolioSkillsFrontendList = userPortfolioSkillsFrontendRepository.findByUserId(UserInfo.builder().userId(userId).build());
        HashMap<Long, String> userPortfolioSkillsFrontendResult = new HashMap<>();
        for (UserPortfolioSkillsFrontend u: userPortfolioSkillsFrontendList){
            userPortfolioSkillsFrontendResult.put(u.getUserPortfolioSkillsFrontendId(), u.getSkillsFrontend().getImage().getSkillName());
        }
        response.setSkillsFrontendIdList(userPortfolioSkillsFrontendResult);

        List<UserPortfolioSkillsBackend> userPortfolioSkillsBackendListList = userPortfolioSkillsBackendRepository.findByUserId(UserInfo.builder().userId(userId).build());
        HashMap<Long, String> userPortfolioSkillsBackendResult = new HashMap<>();
        for (UserPortfolioSkillsBackend u: userPortfolioSkillsBackendListList){
            userPortfolioSkillsBackendResult.put(u.getUserPortfolioSkillsBackendId(), u.getSkillsBackend().getImage().getSkillName());
        }
        response.setSkillsBackendIdList(userPortfolioSkillsBackendResult);

        List<UserPortfolioSkillsMobileapp> userPortfolioSkillsMobileappList = userPortfolioSkillsMobileappRepository.findByUserId(UserInfo.builder().userId(userId).build());
        HashMap<Long, String> userPortfolioSkillsMobileappResult = new HashMap<>();
        for (UserPortfolioSkillsMobileapp u: userPortfolioSkillsMobileappList){
            userPortfolioSkillsMobileappResult.put(u.getUserPortfolioSkillsMobileappId(), u.getSkillsMobileapp().getImage().getSkillName());
        }
        response.setSkillsMobileappIdList(userPortfolioSkillsMobileappResult);

        List<UserPortfolioSkillsDeployment> userPortfolioSkillsDeploymentList = userPortfolioSkillsDeploymentRepository.findByUserId(UserInfo.builder().userId(userId).build());
        HashMap<Long, String> userPortfolioSkillsDeploymentResult = new HashMap<>();
        for (UserPortfolioSkillsDeployment u: userPortfolioSkillsDeploymentList){
            userPortfolioSkillsDeploymentResult.put(u.getUserPortfolioSkillsDeploymentId(), u.getSkillsDeployment().getImage().getSkillName());
        }
        response.setSkillsDeploymentIdList(userPortfolioSkillsDeploymentResult);

        List<UserPortfolioSkillsVersioncontrol> userPortfolioSkillsVersioncontrolList = userPortfolioSkillsVersioncontrolRepository.findByUserId(UserInfo.builder().userId(userId).build());
        HashMap<Long, String> userPortfolioSkillsVersioncontrolResult = new HashMap<>();
        for (UserPortfolioSkillsVersioncontrol u: userPortfolioSkillsVersioncontrolList){
            userPortfolioSkillsVersioncontrolResult.put(u.getUserPortfolioSkillsVersioncontrolId(), u.getSkillsVersioncontrol().getImage().getSkillName());
        }
        response.setSkillsVersioncontrolIdList(userPortfolioSkillsVersioncontrolResult);

        List<UserPortfolioSkillsCommunication> userPortfolioSkillsCommunicationList = userPortfolioSkillsCommunicationRepository.findByUserId(UserInfo.builder().userId(userId).build());
        HashMap<Long, String> userPortfolioSkillsCommunicationResult = new HashMap<>();
        for (UserPortfolioSkillsCommunication u: userPortfolioSkillsCommunicationList){
            userPortfolioSkillsCommunicationResult.put(u.getUserPortfolioSkillsCommunicationId(), u.getSkillsCommunication().getImage().getSkillName());
        }
        response.setSkillsCommunicationIdList(userPortfolioSkillsCommunicationResult);

        List<UserPortfolioSkillsCertification> userPortfolioSkillsCertificationList = userPortfolioSkillsCertificationRepository.findByUserId(UserInfo.builder().userId(userId).build());
        HashMap<Long, String> userPortfolioSkillsCertificationResult = new HashMap<>();
        for (UserPortfolioSkillsCertification u: userPortfolioSkillsCertificationList){
            userPortfolioSkillsCertificationResult.put(u.getUserPortfolioSkillsCertificationId(), u.getSkillsCertification().getImage().getSkillName());
        }
        response.setSkillsCertificationIdList(userPortfolioSkillsCertificationResult);
        return response;
    }

    @Override
    public Boolean addSkills(String userId, PortfolioSkillsRequest request) throws RuntimeException {
        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));

        if (request.getCategory().equals("frontend")){
            skillsFrontendRepository.findById(request.getSkillsId())
                    .orElseThrow(() -> new NoSuchDataException("존재하지 않는 skills_frontend_id 입니다.", request.getSkillsId().toString(), ResCode.NO_SUCH_DATA.value()));

            Optional<Long> exists = userPortfolioSkillsFrontendRepository.findEntityCnt(
                    UserInfo.builder().userId(userId).build(),
                    SkillsFrontend.builder().skillsFontendId(request.getSkillsId()).build());

            if(exists.get() == 0) {
                userPortfolioSkillsFrontendRepository.save(UserPortfolioSkillsFrontend.builder()
                        .skillsFrontend(SkillsFrontend.builder().skillsFontendId(request.getSkillsId()).build())
                        .userInfo(UserInfo.builder().userId(userId).build())
                        .build());
            }else{
                throw new DuplicateKeyException("이미 존재하는 정보입니다.", request.getSkillsId().toString(), ResCode.DUPLICATE_KEY.value());
            }
        }else if(request.getCategory().equals("backend")){
            skillsBackendRepository.findById(request.getSkillsId())
                    .orElseThrow(() -> new NoSuchDataException("존재하지 않는 skills_backend_id 입니다.", request.getSkillsId().toString(), ResCode.NO_SUCH_DATA.value()));

            Optional<Long> exists = userPortfolioSkillsBackendRepository.findEntityCnt(
                    UserInfo.builder().userId(userId).build(),
                    SkillsBackend.builder().skillsBackendId(request.getSkillsId()).build());

            if(exists.get() == 0) {
                userPortfolioSkillsBackendRepository.save(UserPortfolioSkillsBackend.builder()
                        .skillsBackend(SkillsBackend.builder().skillsBackendId(request.getSkillsId()).build())
                        .userInfo(UserInfo.builder().userId(userId).build())
                        .build());
            }else{
                throw new DuplicateKeyException("이미 존재하는 정보입니다.", request.getSkillsId().toString(), ResCode.DUPLICATE_KEY.value());
            }
        }else if(request.getCategory().equals("deployment")){
            skillsDeploymentRepository.findById(request.getSkillsId())
                    .orElseThrow(() -> new NoSuchDataException("존재하지 않는 skills_deployment_id 입니다.", request.getSkillsId().toString(), ResCode.NO_SUCH_DATA.value()));

            Optional<Long> exists = userPortfolioSkillsDeploymentRepository.findEntityCnt(
                    UserInfo.builder().userId(userId).build(),
                    SkillsDeployment.builder().skillsDeploymentId(request.getSkillsId()).build());

            if(exists.get() == 0) {
                userPortfolioSkillsDeploymentRepository.save(UserPortfolioSkillsDeployment.builder()
                        .skillsDeployment(SkillsDeployment.builder().skillsDeploymentId(request.getSkillsId()).build())
                        .userInfo(UserInfo.builder().userId(userId).build())
                        .build());
            }else{
                throw new DuplicateKeyException("이미 존재하는 정보입니다.", request.getSkillsId().toString(), ResCode.DUPLICATE_KEY.value());
            }
        }else if(request.getCategory().equals("mobileapp")){
            skillsMobileappRepository.findById(request.getSkillsId())
                    .orElseThrow(() -> new NoSuchDataException("존재하지 않는 skills_mobileapp_id 입니다.", request.getSkillsId().toString(), ResCode.NO_SUCH_DATA.value()));

            Optional<Long> exists = userPortfolioSkillsMobileappRepository.findEntityCnt(
                    UserInfo.builder().userId(userId).build(),
                    SkillsMobileapp.builder().skillsMobileappId(request.getSkillsId()).build());

            if(exists.get() == 0) {
                userPortfolioSkillsMobileappRepository.save(UserPortfolioSkillsMobileapp.builder()
                        .skillsMobileapp(SkillsMobileapp.builder().skillsMobileappId(request.getSkillsId()).build())
                        .userInfo(UserInfo.builder().userId(userId).build())
                        .build());
            }else{
                throw new DuplicateKeyException("이미 존재하는 정보입니다.", request.getSkillsId().toString(), ResCode.DUPLICATE_KEY.value());
            }
        }else if(request.getCategory().equals("versioncontrol")){
            skillsVersioncontrolRepository.findById(request.getSkillsId())
                    .orElseThrow(() -> new NoSuchDataException("존재하지 않는 skills_versioncontrol_id 입니다.", request.getSkillsId().toString(), ResCode.NO_SUCH_DATA.value()));

            Optional<Long> exists = userPortfolioSkillsVersioncontrolRepository.findEntityCnt(
                    UserInfo.builder().userId(userId).build(),
                    SkillsVersioncontrol.builder().skillsVersioncontrolId(request.getSkillsId()).build());

            if(exists.get() == 0) {
                userPortfolioSkillsVersioncontrolRepository.save(UserPortfolioSkillsVersioncontrol.builder()
                        .skillsVersioncontrol(SkillsVersioncontrol.builder().skillsVersioncontrolId(request.getSkillsId()).build())
                        .userInfo(UserInfo.builder().userId(userId).build())
                        .build());
            }else{
                throw new DuplicateKeyException("이미 존재하는 정보입니다.", request.getSkillsId().toString(), ResCode.DUPLICATE_KEY.value());
            }
        }else if(request.getCategory().equals("communication")){
            skillsCommunicationRepository.findById(request.getSkillsId())
                    .orElseThrow(() -> new NoSuchDataException("존재하지 않는 skills_communication_id 입니다.", request.getSkillsId().toString(), ResCode.NO_SUCH_DATA.value()));

            Optional<Long> exists = userPortfolioSkillsCommunicationRepository.findEntityCnt(
                    UserInfo.builder().userId(userId).build(),
                    SkillsCommunication.builder().skillsCommunicationId(request.getSkillsId()).build());

            if(exists.get() == 0) {
                userPortfolioSkillsCommunicationRepository.save(UserPortfolioSkillsCommunication.builder()
                        .skillsCommunication(SkillsCommunication.builder().skillsCommunicationId(request.getSkillsId()).build())
                        .userInfo(UserInfo.builder().userId(userId).build())
                        .build());
            }else{
                throw new DuplicateKeyException("이미 존재하는 정보입니다.", request.getSkillsId().toString(), ResCode.DUPLICATE_KEY.value());
            }
        }else if(request.getCategory().equals("certification")){
            skillsCertificationRepository.findById(request.getSkillsId())
                    .orElseThrow(() -> new NoSuchDataException("존재하지 않는 skills_certification_id 입니다.", request.getSkillsId().toString(), ResCode.NO_SUCH_DATA.value()));

            Optional<Long> exists = userPortfolioSkillsCertificationRepository.findEntityCnt(
                    UserInfo.builder().userId(userId).build(),
                    SkillsCertification.builder().skillsCertificationId(request.getSkillsId()).build());

            if(exists.get() == 0) {
                userPortfolioSkillsCertificationRepository.save(UserPortfolioSkillsCertification.builder()
                        .skillsCertification(SkillsCertification.builder().skillsCertificationId(request.getSkillsId()).build())
                        .userInfo(UserInfo.builder().userId(userId).build())
                        .build());
            }else{
                throw new DuplicateKeyException("이미 존재하는 정보입니다.", request.getSkillsId().toString(), ResCode.DUPLICATE_KEY.value());
            }
        }
        return true;
    }

    @Override
    public Boolean deleteSkills(String userId, PortfolioSkillsRequest request) throws RuntimeException {
        if (request.getCategory().equals("frontend")) {
            UserPortfolioSkillsFrontend exists = userPortfolioSkillsFrontendRepository.findEntity(
                            UserInfo.builder().userId(userId).build(),
                            SkillsFrontend.builder().skillsFontendId(request.getSkillsId()).build())
                    .orElseThrow(() -> new NoSuchDataException("존재하지 않는 skills_communication_id 입니다.", request.getSkillsId().toString(), ResCode.NO_SUCH_DATA.value()));

            userPortfolioSkillsFrontendRepository.deleteById(exists.getUserPortfolioSkillsFrontendId());

        }else if(request.getCategory().equals("backend")) {
            UserPortfolioSkillsBackend exists = userPortfolioSkillsBackendRepository.findEntity(
                            UserInfo.builder().userId(userId).build(),
                            SkillsBackend.builder().skillsBackendId(request.getSkillsId()).build())
                    .orElseThrow(() -> new NoSuchDataException("존재하지 않는 skills_backend_id 입니다.", request.getSkillsId().toString(), ResCode.NO_SUCH_DATA.value()));

            userPortfolioSkillsBackendRepository.deleteById(exists.getUserPortfolioSkillsBackendId());

        }else if(request.getCategory().equals("deployment")) {
            UserPortfolioSkillsDeployment exists = userPortfolioSkillsDeploymentRepository.findEntity(
                            UserInfo.builder().userId(userId).build(),
                            SkillsDeployment.builder().skillsDeploymentId(request.getSkillsId()).build())
                    .orElseThrow(() -> new NoSuchDataException("존재하지 않는 skills_deployment_id 입니다.", request.getSkillsId().toString(), ResCode.NO_SUCH_DATA.value()));

            userPortfolioSkillsDeploymentRepository.deleteById(exists.getUserPortfolioSkillsDeploymentId());

        }else if(request.getCategory().equals("mobileapp")) {
            UserPortfolioSkillsMobileapp exists = userPortfolioSkillsMobileappRepository.findEntity(
                            UserInfo.builder().userId(userId).build(),
                            SkillsMobileapp.builder().skillsMobileappId(request.getSkillsId()).build())
                    .orElseThrow(() -> new NoSuchDataException("존재하지 않는 skills_mobileapp_id 입니다.", request.getSkillsId().toString(), ResCode.NO_SUCH_DATA.value()));

            userPortfolioSkillsMobileappRepository.deleteById(exists.getUserPortfolioSkillsMobileappId());

        }else if(request.getCategory().equals("versioncontrol")) {
            UserPortfolioSkillsVersioncontrol exists = userPortfolioSkillsVersioncontrolRepository.findEntity(
                            UserInfo.builder().userId(userId).build(),
                            SkillsVersioncontrol.builder().skillsVersioncontrolId(request.getSkillsId()).build())
                    .orElseThrow(() -> new NoSuchDataException("존재하지 않는 skills_versioncontrol_id 입니다.", request.getSkillsId().toString(), ResCode.NO_SUCH_DATA.value()));

            userPortfolioSkillsVersioncontrolRepository.deleteById(exists.getUserPortfolioSkillsVersioncontrolId());

        }else if(request.getCategory().equals("communication")) {
            UserPortfolioSkillsCommunication exists = userPortfolioSkillsCommunicationRepository.findEntity(
                            UserInfo.builder().userId(userId).build(),
                            SkillsCommunication.builder().skillsCommunicationId(request.getSkillsId()).build())
                    .orElseThrow(() -> new NoSuchDataException("존재하지 않는 skills_communication_id 입니다.", request.getSkillsId().toString(), ResCode.NO_SUCH_DATA.value()));

            userPortfolioSkillsCommunicationRepository.deleteById(exists.getUserPortfolioSkillsCommunicationId());

        }else if(request.getCategory().equals("certification")) {
            UserPortfolioSkillsCertification exists = userPortfolioSkillsCertificationRepository.findEntity(
                            UserInfo.builder().userId(userId).build(),
                            SkillsCertification.builder().skillsCertificationId(request.getSkillsId()).build())
                    .orElseThrow(() -> new NoSuchDataException("존재하지 않는 skills_certification_id 입니다.", request.getSkillsId().toString(), ResCode.NO_SUCH_DATA.value()));

            userPortfolioSkillsCertificationRepository.deleteById(exists.getUserPortfolioSkillsCertificationId());

        }

        return true;
    }

    @Override
    public List<PortfolioSkillsListResponse> findSkillsList(String imageCategory) throws RuntimeException {

        List<PortfolioSkillsListResponse> response = new ArrayList<>();
        if(imageCategory.equals("frontend")){
            List<SkillsFrontend> skillsFrontendList = skillsFrontendRepository.findAll();
            for(SkillsFrontend s: skillsFrontendList){
                response.add(new PortfolioSkillsListResponse(s.getSkillsFontendId(), s.getImage().getSkillName()));
            }
        }else if(imageCategory.equals("backend")){
            List<SkillsBackend> skillsBackendList = skillsBackendRepository.findAll();
            for(SkillsBackend s: skillsBackendList){
                response.add(new PortfolioSkillsListResponse(s.getSkillsBackendId(), s.getImage().getSkillName()));
            }
        }else if(imageCategory.equals("deployment")){
            List<SkillsDeployment> skillsDeploymentList = skillsDeploymentRepository.findAll();
            for(SkillsDeployment s: skillsDeploymentList){
                response.add(new PortfolioSkillsListResponse(s.getSkillsDeploymentId(), s.getImage().getSkillName()));
            }
        }else if(imageCategory.equals("mobileapp")){
            List<SkillsMobileapp> skillsMobileappList = skillsMobileappRepository.findAll();
            for(SkillsMobileapp s: skillsMobileappList){
                response.add(new PortfolioSkillsListResponse(s.getSkillsMobileappId(), s.getImage().getSkillName()));
            }
        }else if(imageCategory.equals("communication")){
            List<SkillsCommunication> skillsCommunicationList = skillsCommunicationRepository.findAll();
            for(SkillsCommunication s: skillsCommunicationList){
                response.add(new PortfolioSkillsListResponse(s.getSkillsCommunicationId(), s.getImage().getSkillName()));
            }
        }else if(imageCategory.equals("certification")){
            List<SkillsCertification> skillsCertificationList = skillsCertificationRepository.findAll();
            for(SkillsCertification s: skillsCertificationList){
                response.add(new PortfolioSkillsListResponse(s.getSkillsCertificationId(), s.getImage().getSkillName()));
            }
        }else if(imageCategory.equals("versioncontrol")){
            List<SkillsVersioncontrol> skillsVersioncontrolList = skillsVersioncontrolRepository.findAll();
            for(SkillsVersioncontrol s: skillsVersioncontrolList){
                response.add(new PortfolioSkillsListResponse(s.getSkillsVersioncontrolId(), s.getImage().getSkillName()));
            }
        }else{
            throw new NoSuchDataException("해당 카테고리는 존재하지 않습니다.", imageCategory, ResCode.NO_SUCH_DATA.value());
        }

        return response;
    }

    @Override
    public List<PortfolioArchivingResponse> findArchiving(String userId) throws RuntimeException {
        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));
        List<PortfolioArchiving> portfolioArchivingList = portfolioArchivingRepository.findByUserId(UserInfo.builder().userId(userId).build())
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 정보 입니다.", userId, ResCode.NO_SUCH_DATA.value()));
        List<PortfolioArchivingResponse> response = new ArrayList<>();
        for (PortfolioArchiving p: portfolioArchivingList){
            response.add(new PortfolioArchivingResponse(p.getPortfolioArchivingId(), p.getArchivingName(), p.getArchivingExplanation()));
        }

        return response;
    }

    @Override
    public Boolean addArchiving(String userId, PortfolioArchivingRequest request) throws RuntimeException {
        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));
        portfolioArchivingRepository.save(PortfolioArchiving.builder()
                .archivingExplanation(request.getArchivingExplanation())
                .archivingName(request.getArchivingName())
                .userInfo(UserInfo.builder().userId(userId).build())
                .build());
        return true;
    }

    @Override
    public Boolean updateArchiving(String userId, PortfolioArchivingRequest request) throws RuntimeException {
        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));
        portfolioArchivingRepository.findById(request.getPortfolioArchivingId())
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 정보 입니다.", request.getPortfolioArchivingId().toString(), ResCode.NO_SUCH_DATA.value()));
        portfolioArchivingRepository.save(PortfolioArchiving.builder()
                .portfolioArchivingId(request.getPortfolioArchivingId())
                .archivingExplanation(request.getArchivingExplanation())
                .archivingName(request.getArchivingName())
                .userInfo(UserInfo.builder().userId(userId).build())
                .build());
        return true;
    }

    @Override
    public Boolean deleteArchiving(String userId, PortfolioArchivingRequest request) throws RuntimeException {
        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));
        portfolioArchivingRepository.findById(request.getPortfolioArchivingId())
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 정보 입니다.", request.getPortfolioArchivingId().toString(), ResCode.NO_SUCH_DATA.value()));
        portfolioArchivingRepository.deleteById(request.getPortfolioArchivingId());
        return true;
    }

    @Override
    public List<PortfolioCareerResponse> findCareer(String userId) throws RuntimeException {
        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));
        List<PortfolioCareer> portfolioCareerList = portfolioCareerRepository.findByUserId(UserInfo.builder().userId(userId).build())
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 정보 입니다.", userId, ResCode.NO_SUCH_DATA.value()));
        List<PortfolioCareerResponse> response = new ArrayList<>();
        for (PortfolioCareer p: portfolioCareerList){
            response.add(new PortfolioCareerResponse(p.getPortfolioCareerId(), p.getCareerExplanation()));
        }

        return response;
    }

    @Override
    public Boolean addCareer(String userId, PortfolioCareerRequest request) throws RuntimeException {
        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));
        portfolioCareerRepository.save(PortfolioCareer.builder()
                .careerExplanation(request.getCareerExplanation())
                .userInfo(UserInfo.builder().userId(userId).build())
                .build());
        return true;
    }

    @Override
    public Boolean updateCareer(String userId, PortfolioCareerRequest request) throws RuntimeException {
        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));
        portfolioCareerRepository.findById(request.getPortfolioCareerId())
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 정보 입니다.", request.getCareerExplanation().toString(), ResCode.NO_SUCH_DATA.value()));
        portfolioCareerRepository.save(PortfolioCareer.builder()
                .portfolioCareerId(request.getPortfolioCareerId())
                .careerExplanation(request.getCareerExplanation())
                .userInfo(UserInfo.builder().userId(userId).build())
                .build());
        return true;
    }

    @Override
    public Boolean deleteCareer(String userId, PortfolioCareerRequest request) throws RuntimeException {
        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));
        portfolioCareerRepository.findById(request.getPortfolioCareerId())
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 정보 입니다.", request.getCareerExplanation().toString(), ResCode.NO_SUCH_DATA.value()));
        portfolioCareerRepository.deleteById(request.getPortfolioCareerId());

        return true;

    }

    @Override
    public List<PortfolioProjectResponse> findProject(String userId) throws RuntimeException {
        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));
        List<PortfolioProject> portfolioProjectList = portfolioProjectRepository.findByUserId(UserInfo.builder().userId(userId).build())
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 정보 입니다.", userId, ResCode.NO_SUCH_DATA.value()));
        List<PortfolioProjectResponse> response = new ArrayList<>();
        for (PortfolioProject p: portfolioProjectList){
            response.add(new PortfolioProjectResponse(p.getPortfolioProjectId(), p.getProjectName(), p.getProjectExplanation()));
        }

        return response;
    }

    @Override
    public Boolean addProject(String userId, PortfolioProjectRequest request) throws RuntimeException {
        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));
        portfolioProjectRepository.save(PortfolioProject.builder()
                .projectExplanation(request.getProjectExplanation())
                .projectName(request.getProjectName())
                .userInfo(UserInfo.builder().userId(userId).build())
                .build());
        return true;
    }

    @Override
    public Boolean updateProject(String userId, PortfolioProjectRequest request) throws RuntimeException {
        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));
        portfolioProjectRepository.findById(request.getPortfolioProjectId())
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 정보 입니다.", request.getPortfolioProjectId().toString(), ResCode.NO_SUCH_DATA.value()));
        portfolioProjectRepository.save(PortfolioProject.builder()
                .portfolioProjectId(request.getPortfolioProjectId())
                .projectExplanation(request.getProjectExplanation())
                .projectName(request.getProjectName())
                .userInfo(UserInfo.builder().userId(userId).build())
                .build());
        return true;
    }

    @Override
    public Boolean deleteProject(String userId, PortfolioProjectRequest request) throws RuntimeException {
        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));
        portfolioProjectRepository.findById(request.getPortfolioProjectId())
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 정보 입니다.", request.getPortfolioProjectId().toString(), ResCode.NO_SUCH_DATA.value()));
        portfolioProjectRepository.deleteById(request.getPortfolioProjectId());
        return true;
    }

    @Override
    public ImageDownloadResponse download(String imageName) throws RuntimeException{
        Optional<Image> image = imageRepository.findById(imageName);
        if(image.isEmpty()){
            throw new NoSuchDataException("해당 이미지가 존재하지 않습니다.", imageName, ResCode.NO_SUCH_DATA.value());
        }
        try {
            ImageDownloadResponse response = new ImageDownloadResponse();
            byte[] imageBytes = Files.readAllBytes(Paths.get(new File("").getAbsolutePath() + File.separator + image.get().getImageUrl()));
            response.setBase64Images(Base64.getEncoder().encodeToString(imageBytes));
            return response;
        }catch (Exception ex){
            throw new UnknownException(ex.getMessage(), "unknown", ResCode.UNKNOWN.value());
        }
    }
}
