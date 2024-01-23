package bcsd.backend.project.pokku.dto.PortfolioSkills;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioSkillsResponse {
    private List<Long> skillsFrontendIdList;
    private List<Long> skillsBackendIdList;
    private List<Long> skillsMobileappIdList;
    private List<Long> skillsDeploymentIdList;
    private List<Long> skillsVersioncontrolIdList;
    private List<Long> skillsCertificationIdList;
    private List<Long> skillsCommunicationIdList;
}
