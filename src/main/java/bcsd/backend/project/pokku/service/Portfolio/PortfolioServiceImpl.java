package bcsd.backend.project.pokku.service.Portfolio;

import bcsd.backend.project.pokku.dao.*;
import bcsd.backend.project.pokku.domain.*;
import bcsd.backend.project.pokku.dto.Portfolio.PortfolioRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

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

    @Override
    public Boolean addSkills(PortfolioRequest request) throws Exception{

        if (request.getCategory().equals("frontend")){
            skillsFrontendRepository.findById(request.getSkillsId())
                    .orElseThrow(() -> new Exception("존재하지 않는 skills_frontend_id 입니다."));

            Optional<Long> exists = userPortfolioSkillsFrontendRepository.findEntityCnt(
                    UserInfo.builder().userId(request.getUserId()).build(),
                    SkillsFrontend.builder().skillsFontendId(request.getSkillsId()).build());

            if(exists.get() == 0) {
                userPortfolioSkillsFrontendRepository.save(UserPortfolioSkillsFrontend.builder()
                        .skillsFrontend(SkillsFrontend.builder().skillsFontendId(request.getSkillsId()).build())
                        .userInfo(UserInfo.builder().userId(request.getUserId()).build())
                        .build());
            }
        }else if(request.getCategory().equals("backend")){
            skillsBackendRepository.findById(request.getSkillsId())
                    .orElseThrow(() -> new Exception("존재하지 않는 skills_backend_id 입니다."));

            Optional<Long> exists = userPortfolioSkillsBackendRepository.findEntityCnt(
                    UserInfo.builder().userId(request.getUserId()).build(),
                    SkillsBackend.builder().skillsBackendId(request.getSkillsId()).build());

            if(exists.get() == 0) {
                userPortfolioSkillsBackendRepository.save(UserPortfolioSkillsBackend.builder()
                        .skillsBackend(SkillsBackend.builder().skillsBackendId(request.getSkillsId()).build())
                        .userInfo(UserInfo.builder().userId(request.getUserId()).build())
                        .build());
            }
        }else if(request.getCategory().equals("deployment")){
            skillsDeploymentRepository.findById(request.getSkillsId())
                    .orElseThrow(() -> new Exception("존재하지 않는 skills_deployment_id 입니다."));

            Optional<Long> exists = userPortfolioSkillsDeploymentRepository.findEntityCnt(
                    UserInfo.builder().userId(request.getUserId()).build(),
                    SkillsDeployment.builder().skillsDeploymentId(request.getSkillsId()).build());

            if(exists.get() == 0) {
                userPortfolioSkillsDeploymentRepository.save(UserPortfolioSkillsDeployment.builder()
                        .skillsDeployment(SkillsDeployment.builder().skillsDeploymentId(request.getSkillsId()).build())
                        .userInfo(UserInfo.builder().userId(request.getUserId()).build())
                        .build());
            }
        }else if(request.getCategory().equals("mobileapp")){
            skillsMobileappRepository.findById(request.getSkillsId())
                    .orElseThrow(() -> new Exception("존재하지 않는 skills_mobileapp_id 입니다."));

            Optional<Long> exists = userPortfolioSkillsMobileappRepository.findEntityCnt(
                    UserInfo.builder().userId(request.getUserId()).build(),
                    SkillsMobileapp.builder().skillsMobileappId(request.getSkillsId()).build());

            if(exists.get() == 0) {
                userPortfolioSkillsMobileappRepository.save(UserPortfolioSkillsMobileapp.builder()
                        .skillsMobileapp(SkillsMobileapp.builder().skillsMobileappId(request.getSkillsId()).build())
                        .userInfo(UserInfo.builder().userId(request.getUserId()).build())
                        .build());
            }
        }else if(request.getCategory().equals("versioncontrol")){
            skillsVersioncontrolRepository.findById(request.getSkillsId())
                    .orElseThrow(() -> new Exception("존재하지 않는 skills_versioncontrol_id 입니다."));

            Optional<Long> exists = userPortfolioSkillsVersioncontrolRepository.findEntityCnt(
                    UserInfo.builder().userId(request.getUserId()).build(),
                    SkillsVersioncontrol.builder().skillsVersioncontrolId(request.getSkillsId()).build());

            if(exists.get() == 0) {
                userPortfolioSkillsVersioncontrolRepository.save(UserPortfolioSkillsVersioncontrol.builder()
                        .skillsVersioncontrol(SkillsVersioncontrol.builder().skillsVersioncontrolId(request.getSkillsId()).build())
                        .userInfo(UserInfo.builder().userId(request.getUserId()).build())
                        .build());
            }
        }else if(request.getCategory().equals("communication")){
            skillsCommunicationRepository.findById(request.getSkillsId())
                    .orElseThrow(() -> new Exception("존재하지 않는 skills_communication_id 입니다."));

            Optional<Long> exists = userPortfolioSkillsCommunicationRepository.findEntityCnt(
                    UserInfo.builder().userId(request.getUserId()).build(),
                    SkillsCommunication.builder().skillsCommunicationId(request.getSkillsId()).build());

            if(exists.get() == 0) {
                userPortfolioSkillsCommunicationRepository.save(UserPortfolioSkillsCommunication.builder()
                        .skillsCommunication(SkillsCommunication.builder().skillsCommunicationId(request.getSkillsId()).build())
                        .userInfo(UserInfo.builder().userId(request.getUserId()).build())
                        .build());
            }
        }

        return true;
    }

    @Override
    public Boolean deleteSkills(PortfolioRequest request) throws Exception{
        if (request.getCategory().equals("frontend")) {
            UserPortfolioSkillsFrontend exists = userPortfolioSkillsFrontendRepository.findEntity(
                            UserInfo.builder().userId(request.getUserId()).build(),
                            SkillsFrontend.builder().skillsFontendId(request.getSkillsId()).build())
                    .orElseThrow(() -> new BadCredentialsException("유효하지 않은 데이터 입니다."));

            userPortfolioSkillsFrontendRepository.deleteById(exists.getUserPortfolioSkillsFrontendId());

        }else if(request.getCategory().equals("backend")) {
            UserPortfolioSkillsBackend exists = userPortfolioSkillsBackendRepository.findEntity(
                            UserInfo.builder().userId(request.getUserId()).build(),
                            SkillsBackend.builder().skillsBackendId(request.getSkillsId()).build())
                    .orElseThrow(() -> new BadCredentialsException("유효하지 않은 데이터 입니다."));

            userPortfolioSkillsBackendRepository.deleteById(exists.getUserPortfolioSkillsBackendId());

        }else if(request.getCategory().equals("deployment")) {
            UserPortfolioSkillsDeployment exists = userPortfolioSkillsDeploymentRepository.findEntity(
                            UserInfo.builder().userId(request.getUserId()).build(),
                            SkillsDeployment.builder().skillsDeploymentId(request.getSkillsId()).build())
                    .orElseThrow(() -> new BadCredentialsException("유효하지 않은 데이터 입니다."));

            userPortfolioSkillsDeploymentRepository.deleteById(exists.getUserPortfolioSkillsDeploymentId());

        }else if(request.getCategory().equals("mobileapp")) {
            UserPortfolioSkillsMobileapp exists = userPortfolioSkillsMobileappRepository.findEntity(
                            UserInfo.builder().userId(request.getUserId()).build(),
                            SkillsMobileapp.builder().skillsMobileappId(request.getSkillsId()).build())
                    .orElseThrow(() -> new BadCredentialsException("유효하지 않은 데이터 입니다."));

            userPortfolioSkillsMobileappRepository.deleteById(exists.getUserPortfolioSkillsMobileappId());

        }else if(request.getCategory().equals("versioncontrol")) {
            UserPortfolioSkillsVersioncontrol exists = userPortfolioSkillsVersioncontrolRepository.findEntity(
                            UserInfo.builder().userId(request.getUserId()).build(),
                            SkillsVersioncontrol.builder().skillsVersioncontrolId(request.getSkillsId()).build())
                    .orElseThrow(() -> new BadCredentialsException("유효하지 않은 데이터 입니다."));

            userPortfolioSkillsVersioncontrolRepository.deleteById(exists.getUserPortfolioSkillsVersioncontrolId());

        }else if(request.getCategory().equals("communication")) {
            UserPortfolioSkillsCommunication exists = userPortfolioSkillsCommunicationRepository.findEntity(
                            UserInfo.builder().userId(request.getUserId()).build(),
                            SkillsCommunication.builder().skillsCommunicationId(request.getSkillsId()).build())
                    .orElseThrow(() -> new BadCredentialsException("유효하지 않은 데이터 입니다."));

            userPortfolioSkillsCommunicationRepository.deleteById(exists.getUserPortfolioSkillsCommunicationId());

        }

        return true;
    }

}
