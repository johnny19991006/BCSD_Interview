package bcsd.backend.project.pokku.service.PortfolioProject;

import bcsd.backend.project.pokku.dao.PortfolioProjectRepository;
import bcsd.backend.project.pokku.domain.PortfolioArchiving;
import bcsd.backend.project.pokku.domain.PortfolioProject;
import bcsd.backend.project.pokku.domain.UserInfo;
import bcsd.backend.project.pokku.dto.PortfolioArchiving.PortfolioArchivingRequest;
import bcsd.backend.project.pokku.dto.PortfolioProject.PortfolioProjectRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PortfolioProjectServiceImpl implements PortfolioProjectService{

    private final PortfolioProjectRepository portfolioProjectRepository;
    @Override
    public Boolean addProject(PortfolioProjectRequest request) throws Exception{
        portfolioProjectRepository.save(PortfolioProject.builder()
                .projectExplanation(request.getProjectExplanation())
                .projectName(request.getProjectName())
                .userInfo(UserInfo.builder().userId(request.getUserId()).build())
                .build());
        return true;
    }

    @Override
    public Boolean deleteProject(PortfolioProjectRequest request) throws Exception{

        portfolioProjectRepository.deleteByUserIdAndName(
                UserInfo.builder().userId(request.getUserId()).build(),
                request.getProjectName());
        return true;
    }
}
