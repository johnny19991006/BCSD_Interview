package bcsd.backend.project.pokku.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@Table(name = "skills_deployment")
@AllArgsConstructor
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

}
