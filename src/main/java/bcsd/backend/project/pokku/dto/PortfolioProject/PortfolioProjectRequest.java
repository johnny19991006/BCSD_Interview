package bcsd.backend.project.pokku.dto.PortfolioProject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortfolioProjectRequest {
    private String userId;
    private String projectName;
    private String projectExplanation;
}
