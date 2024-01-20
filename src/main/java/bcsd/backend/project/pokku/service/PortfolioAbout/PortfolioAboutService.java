package bcsd.backend.project.pokku.service.PortfolioAbout;

import bcsd.backend.project.pokku.dto.PortfolioAbout.PortfolioAboutRequest;
import bcsd.backend.project.pokku.dto.PortfolioAbout.PortfolioAboutResponse;

public interface PortfolioAboutService {

    public PortfolioAboutResponse findPortfolioAbout(PortfolioAboutRequest request) throws Exception;
    public boolean updatePortfolioAbout(PortfolioAboutRequest request) throws Exception;
}
