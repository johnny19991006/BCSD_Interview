package bcsd.backend.project.pokku.service.PortfolioProject;

import bcsd.backend.project.pokku.dto.PortfolioProject.PortfolioProjectRequest;

public interface PortfolioProjectService {
    public Boolean addProject(PortfolioProjectRequest request) throws Exception;
    public Boolean deleteProject(PortfolioProjectRequest request) throws Exception;
}
