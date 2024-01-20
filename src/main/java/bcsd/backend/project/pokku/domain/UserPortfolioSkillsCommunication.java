package bcsd.backend.project.pokku.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_portfolio_skills_communication")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPortfolioSkillsCommunication {

    @Id
    @Column(name = "user_portfolio_skills_communication_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPortfolioSkillsCommunicationId;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserInfo userInfo;

    @JoinColumn(name = "skills_communication_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private SkillsCommunication skillsCommunication;

}
