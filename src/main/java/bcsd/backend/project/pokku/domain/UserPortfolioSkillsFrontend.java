package bcsd.backend.project.pokku.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_portfolio_skills_frontend")
@Setter
@Getter
@Builder
@AllArgsConstructor
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

}
