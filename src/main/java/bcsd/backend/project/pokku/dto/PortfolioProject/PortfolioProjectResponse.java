package bcsd.backend.project.pokku.dto.PortfolioProject;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PortfolioProjectResponse {
    private Long portfolioProjectId;
    private String projectName;
    private String projectExplanation;

    public PortfolioProjectResponse(Long portfolioProjectId, String projectName, String projectExplanation){
        this.portfolioProjectId = portfolioProjectId;
        this.projectName = projectName;
        this.projectExplanation = projectExplanation;
    }
}
