package bcsd.backend.project.pokku.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "skills_deployment")
@NoArgsConstructor
public class SkillsDeployment {

    @Id
    @Column(name = "skills_deployment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillsDeploymentId;

    @JoinColumn(name = "skill_name")
    @OneToOne(fetch = FetchType.LAZY)
    private Image image;

    @OneToMany(mappedBy = "skillsDeployment", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserPortfolioSkillsDeployment> userPortfolioSkillsDeploymentList = new ArrayList<>();

    @Builder
    public SkillsDeployment(Long skillsDeploymentId, Image image){
        this.skillsDeploymentId = skillsDeploymentId;
        this.image = image;
    }

}
