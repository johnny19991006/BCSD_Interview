package bcsd.backend.project.pokku.service;

import bcsd.backend.project.pokku.dao.PortfolioAboutRepository;
import bcsd.backend.project.pokku.domain.PortfolioAbout;
import bcsd.backend.project.pokku.domain.UserInfo;
import bcsd.backend.project.pokku.dto.PortfolioAboutRequest;
import bcsd.backend.project.pokku.dto.PortfolioAboutResponse;
import bcsd.backend.project.pokku.dto.UserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
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

    @Override
    public PortfolioAboutResponse findPortfolioAbout(PortfolioAboutRequest request) throws Exception{
        PortfolioAbout portfolioAbout = portfolioAboutRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new BadCredentialsException("잘못된 계정 정보 입니다."));

        return new PortfolioAboutResponse(portfolioAbout);
    }
}
