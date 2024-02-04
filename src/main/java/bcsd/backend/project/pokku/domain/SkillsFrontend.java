package bcsd.backend.project.pokku.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "skills_frontend")
@NoArgsConstructor
public class SkillsFrontend {

    @Id
    @Column(name = "skills_frontend_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillsFontendId;

    @JoinColumn(name = "skill_name")
    @OneToOne(fetch = FetchType.LAZY)
    private Image image;

    @OneToMany(mappedBy = "skillsFrontend", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserPortfolioSkillsFrontend> userPortfolioSkillsFrontendList = new ArrayList<>();

    @Builder
    public SkillsFrontend(Long skillsFontendId, Image image){
        this.skillsFontendId = skillsFontendId;
        this.image = image;
    }
}
