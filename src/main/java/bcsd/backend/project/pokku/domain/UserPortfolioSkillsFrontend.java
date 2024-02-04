package bcsd.backend.project.pokku.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_portfolio_skills_frontend")
@Getter
@NoArgsConstructor
public class UserPortfolioSkillsFrontend {

    @Id
    @Column(name = "user_portfolio_skills_frontend_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPortfolioSkillsFrontendId;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserInfo userInfo;

    @JoinColumn(name = "skills_frontend_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private SkillsFrontend skillsFrontend;

    @Builder
    public UserPortfolioSkillsFrontend(SkillsFrontend skillsFrontend, UserInfo userInfo){
        this.skillsFrontend = skillsFrontend;
        this.userInfo = userInfo;
    }

}
