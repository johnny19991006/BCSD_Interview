package bcsd.backend.project.pokku.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_portfolio_skills_certification")
@Getter
@NoArgsConstructor
public class UserPortfolioSkillsCertification {

    @Id
    @Column(name = "user_portfolio_skills_certification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPortfolioSkillsCertificationId;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserInfo userInfo;

    @JoinColumn(name = "skills_certification_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private SkillsCertification skillsCertification;

    @Builder
    public UserPortfolioSkillsCertification(SkillsCertification skillsCertification, UserInfo userInfo){
        this.skillsCertification = skillsCertification;
        this.userInfo = userInfo;
    }

}
