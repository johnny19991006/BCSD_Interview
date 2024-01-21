package bcsd.backend.project.pokku.service.PortfolioArchiving;

import bcsd.backend.project.pokku.dao.PortfolioArchivingRepository;
import bcsd.backend.project.pokku.domain.PortfolioArchiving;
import bcsd.backend.project.pokku.domain.UserInfo;
import bcsd.backend.project.pokku.dto.PortfolioArchiving.PortfolioArchivingRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PortfolioArchivingServiceImpl implements PortfolioArchivingService{

    private final PortfolioArchivingRepository portfolioArchivingRepository;

    @Override
    public Boolean addArchiving(PortfolioArchivingRequest request) throws Exception{
        portfolioArchivingRepository.save(PortfolioArchiving.builder()
                        .archivingExplanation(request.getArchivingExplanation())
                        .archivingName(request.getArchivingName())
                        .userInfo(UserInfo.builder().userId(request.getUserId()).build())
                .build());
        return true;
    }

    @Override
    public Boolean deleteArchiving(PortfolioArchivingRequest request) throws Exception{

        portfolioArchivingRepository.deleteByUserIdAndName(
                UserInfo.builder().userId(request.getUserId()).build(),
                request.getArchivingName());
        return true;
    }
}
