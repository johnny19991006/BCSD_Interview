package bcsd.backend.project.pokku.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "skills_versioncontrol")
@NoArgsConstructor
public class SkillsVersioncontrol {

    @Id
    @Column(name = "skills_versioncontrol_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillsVersioncontrolId;

    @JoinColumn(name = "skill_name")
    @OneToOne(fetch = FetchType.LAZY)
    private Image image;

    @OneToMany(mappedBy = "skillsVersioncontrol", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserPortfolioSkillsVersioncontrol> userPortfolioSkillsVersioncontrolList = new ArrayList<>();

    @Builder
    public SkillsVersioncontrol(Long skillsVersioncontrolId, Image image){
        this.skillsVersioncontrolId = skillsVersioncontrolId;
        this.image = image;
    }

}
