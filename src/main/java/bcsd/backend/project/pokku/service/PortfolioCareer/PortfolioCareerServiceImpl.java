package bcsd.backend.project.pokku.service.PortfolioCareer;

import bcsd.backend.project.pokku.dao.PortfolioCareerRepository;
import bcsd.backend.project.pokku.domain.PortfolioCareer;
import bcsd.backend.project.pokku.domain.UserInfo;
import bcsd.backend.project.pokku.dto.PortfolioCareer.PortfolioCareerRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PortfolioCareerServiceImpl implements PortfolioCareerService{

    private final PortfolioCareerRepository portfolioCareerRepository;

    @Override
    public Boolean addCareer(PortfolioCareerRequest request) throws Exception{
        portfolioCareerRepository.save(PortfolioCareer.builder()
                .careerExplanation(request.getCareerExplanation())
                .userInfo(UserInfo.builder().userId(request.getUserId()).build())
                .build());
        return true;
    }

    @Override
    public Boolean deleteCareer(PortfolioCareerRequest request) throws Exception{

        portfolioCareerRepository.deleteByUserIdAndExplanation(
                UserInfo.builder().userId(request.getUserId()).build(),
                request.getCareerExplanation());

        return true;

    }
}
