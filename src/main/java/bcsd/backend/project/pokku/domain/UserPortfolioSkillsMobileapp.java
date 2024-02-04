package bcsd.backend.project.pokku.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_portfolio_skills_mobileapp")
@Getter
@NoArgsConstructor
public class UserPortfolioSkillsMobileapp {

    @Id
    @Column(name = "user_portfolio_skills_mobileapp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPortfolioSkillsMobileappId;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserInfo userInfo;

    @JoinColumn(name = "skills_mobileapp_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private SkillsMobileapp skillsMobileapp;

    @Builder
    public UserPortfolioSkillsMobileapp(SkillsMobileapp skillsMobileapp, UserInfo userInfo){
        this.skillsMobileapp = skillsMobileapp;
        this.userInfo = userInfo;
    }

}
