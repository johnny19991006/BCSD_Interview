package bcsd.backend.project.pokku.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "skills_certification")
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

    @Builder
    public SkillsCertification(Long skillsCertificationId, Image image){
        this.skillsCertificationId = skillsCertificationId;
        this.image = image;
    }

}
