package bcsd.backend.project.pokku.service.PortfolioArchiving;

import bcsd.backend.project.pokku.dto.PortfolioArchiving.PortfolioArchivingRequest;

public interface PortfolioArchivingService {
    public Boolean addArchiving(PortfolioArchivingRequest request) throws Exception;
    public Boolean deleteArchiving(PortfolioArchivingRequest request) throws Exception;
}
