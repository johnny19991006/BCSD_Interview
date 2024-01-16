package bcsd.backend.project.pokku.service.PortfolioAbout;

import bcsd.backend.project.pokku.dao.PortfolioAboutRepository;
import bcsd.backend.project.pokku.domain.PortfolioAbout;
import bcsd.backend.project.pokku.domain.UserInfo;
import bcsd.backend.project.pokku.dto.PortfolioAbout.PortfolioAboutRequest;
import bcsd.backend.project.pokku.dto.PortfolioAbout.PortfolioAboutResponse;
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
            portfolioAboutRepository.updateById(UserInfo.builder()
                            .userId(request.getUserId())
                            .build(),
                    request.getUserEducationVisible(),
                    request.getUserEmailVisible(),
                    request.getUserNameVisible(),
                    request.getUserTelVisible());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Wrong" + e.getMessage());
        }
        return true;
    }

    @Override
    public PortfolioAboutResponse findPortfolioAbout(PortfolioAboutRequest request) throws Exception{
        PortfolioAbout portfolioAbout = portfolioAboutRepository.findByUserId(UserInfo.builder().userId(request.getUserId()).build())
                .orElseThrow(() -> new BadCredentialsException("잘못된 계정 정보 입니다."));

        return new PortfolioAboutResponse(portfolioAbout);
    }
}
