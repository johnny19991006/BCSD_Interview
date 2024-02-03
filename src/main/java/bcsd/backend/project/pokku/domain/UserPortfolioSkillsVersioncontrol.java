package bcsd.backend.project.pokku.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_portfolio_skills_versioncontrol")
@Getter
@NoArgsConstructor
public class UserPortfolioSkillsVersioncontrol {

    @Id
    @Column(name = "user_portfolio_skills_versioncontrol_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPortfolioSkillsVersioncontrolId;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserInfo userInfo;

    @JoinColumn(name = "skills_versioncontrol_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private SkillsVersioncontrol skillsVersioncontrol;

    @Builder
    public UserPortfolioSkillsVersioncontrol(SkillsVersioncontrol skillsVersioncontrol, UserInfo userInfo){
        this.skillsVersioncontrol = skillsVersioncontrol;
        this.userInfo = userInfo;
    }

}
