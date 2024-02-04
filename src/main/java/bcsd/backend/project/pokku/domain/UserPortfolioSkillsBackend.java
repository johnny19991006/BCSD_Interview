package bcsd.backend.project.pokku.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_portfolio_skills_backend")
@Getter
@NoArgsConstructor
public class UserPortfolioSkillsBackend {

    @Id
    @Column(name = "user_portfolio_skills_backend_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPortfolioSkillsBackendId;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserInfo userInfo;

    @JoinColumn(name = "skills_backend_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private SkillsBackend skillsBackend;

    @Builder
    public UserPortfolioSkillsBackend(SkillsBackend skillsBackend, UserInfo userInfo){
        this.skillsBackend = skillsBackend;
        this.userInfo = userInfo;
    }

}
