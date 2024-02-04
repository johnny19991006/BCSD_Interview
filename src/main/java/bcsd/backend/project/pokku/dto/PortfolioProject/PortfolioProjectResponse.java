package bcsd.backend.project.pokku.dto.PortfolioProject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PortfolioProjectResponse {
    private Long portfolioProjectId;
    private String projectName;
    private String projectExplanation;

}
