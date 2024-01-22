package bcsd.backend.project.pokku.service.PortfolioCareer;

import bcsd.backend.project.pokku.dto.PortfolioArchiving.PortfolioArchivingRequest;
import bcsd.backend.project.pokku.dto.PortfolioCareer.PortfolioCareerRequest;

public interface PortfolioCareerService {
    public Boolean addCareer(PortfolioCareerRequest request) throws Exception;
    public Boolean deleteCareer(PortfolioCareerRequest request) throws Exception;
}
