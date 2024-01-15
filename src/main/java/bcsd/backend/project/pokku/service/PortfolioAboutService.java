package bcsd.backend.project.pokku.service;

import bcsd.backend.project.pokku.dto.PortfolioAboutRequest;
import bcsd.backend.project.pokku.dto.PortfolioAboutResponse;

public interface PortfolioAboutService {

    public PortfolioAboutResponse findPortfolioAbout(PortfolioAboutRequest request) throws Exception;
    public boolean updatePortfolioAbout(PortfolioAboutRequest request) throws Exception;
}
