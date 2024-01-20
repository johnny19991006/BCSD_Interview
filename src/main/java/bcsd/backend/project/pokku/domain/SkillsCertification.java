package bcsd.backend.project.pokku.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@Table(name = "skills_certification")
@AllArgsConstructor
@NoArgsConstructor
public class SkillsCertification {

    @Id
    @Column(name = "skills_certification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillsCertificationId;

    @JoinColumn(name = "skill_name")
    @OneToOne(fetch = FetchType.LAZY)
    private Image image;

    @OneToMany(mappedBy = "skillsCertification", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserPortfolioSkillsCertification> userPortfolioSkillsCertificationList = new ArrayList<>();

}
