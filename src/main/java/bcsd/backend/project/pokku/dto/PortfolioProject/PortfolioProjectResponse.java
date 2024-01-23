package bcsd.backend.project.pokku.dto.PortfolioProject;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioProjectResponse {
    private Long portfolioProjectId;
    private String projectName;
    private String projectExplanation;
}
