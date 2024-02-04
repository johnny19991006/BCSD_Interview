package bcsd.backend.project.pokku.dto.PortfolioSkills;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioSkillsResponse {
    private HashMap<Long, String> skillsFrontendIdList;
    private HashMap<Long, String> skillsBackendIdList;
    private HashMap<Long, String> skillsMobileappIdList;
    private HashMap<Long, String> skillsDeploymentIdList;
    private HashMap<Long, String> skillsVersioncontrolIdList;
    private HashMap<Long, String> skillsCertificationIdList;
    private HashMap<Long, String> skillsCommunicationIdList;
}
