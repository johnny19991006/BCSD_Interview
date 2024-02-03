package bcsd.backend.project.pokku.dto.PortfolioSkills;

import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
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
