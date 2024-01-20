package bcsd.backend.project.pokku.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_portfolio_skills_backend")
@Setter
@Getter
@Builder
@AllArgsConstructor
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

}
