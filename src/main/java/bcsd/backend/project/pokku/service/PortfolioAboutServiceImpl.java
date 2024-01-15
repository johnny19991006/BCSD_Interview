package bcsd.backend.project.pokku.service;

import bcsd.backend.project.pokku.dao.PortfolioAboutRepository;
import bcsd.backend.project.pokku.domain.PortfolioAbout;
import bcsd.backend.project.pokku.domain.UserInfo;
import bcsd.backend.project.pokku.dto.PortfolioAboutRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PortfolioAboutServiceImpl implements PortfolioAboutService{

    private final PortfolioAboutRepository portfolioAboutRepository;

    @Override
    public boolean updatePortfolioAbout(PortfolioAboutRequest request) throws Exception{
        try {
            portfolioAboutRepository.save(PortfolioAbout.builder()
                            .userInfo(UserInfo.builder().userId(request.getUserId()).build())
                            .userEducationVisible(request.getUserEducationVisible())
                            .userNameVisible(request.getUserNameVisible())
                            .userTelVisible(request.getUserTelVisible())
                            .userEmailVisible(request.getUserEmailVisible())
                            .build());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
        return true;
    }
}
