package bcsd.backend.project.pokku.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_portfolio_skills_deployment")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPortfolioSkillsDeployment {

    @Id
    @Column(name = "user_portfolio_skills_deployment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPortfolioSkillsDeploymentId;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserInfo userInfo;

    @JoinColumn(name = "skills_deployment_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private SkillsDeployment skillsDeployment;

}
