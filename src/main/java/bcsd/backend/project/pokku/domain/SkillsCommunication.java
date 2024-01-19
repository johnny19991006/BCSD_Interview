package bcsd.backend.project.pokku.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@Table(name = "skills_communication")
@AllArgsConstructor
@NoArgsConstructor
public class SkillsCommunication {

    @Id
    @Column(name = "skills_communication_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillsCommunicationId;

    @JoinColumn(name = "skill_name")
    @OneToOne(fetch = FetchType.LAZY)
    private Image image;

    @OneToMany(mappedBy = "skillsCommunication", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserPortfolioSkillsCommunication> userPortfolioSkillsCommunicationList = new ArrayList<>();

}
