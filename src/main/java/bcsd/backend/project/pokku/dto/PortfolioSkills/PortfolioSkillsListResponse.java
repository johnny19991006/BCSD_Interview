package bcsd.backend.project.pokku.dto.PortfolioSkills;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PortfolioSkillsListResponse {
    private Long skillId;
    private String skillName;

    public PortfolioSkillsListResponse(Long skillId, String skillName){
        this.skillId = skillId;
        this.skillName = skillName;
    }
}
