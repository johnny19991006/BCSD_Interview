package bcsd.backend.project.pokku.service.Portfolio;

import bcsd.backend.project.pokku.dto.Portfolio.PortfolioRequest;
import bcsd.backend.project.pokku.dto.Portfolio.PortfolioResponse;

public interface PortfolioService {
    public Boolean addSkills(PortfolioRequest request) throws Exception;
    public Boolean deleteSkills(PortfolioRequest request) throws Exception;
}
