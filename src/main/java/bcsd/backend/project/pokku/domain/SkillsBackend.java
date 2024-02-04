package bcsd.backend.project.pokku.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "skills_backend")
@NoArgsConstructor
public class SkillsBackend {

    @Id
    @Column(name = "skills_backend_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillsBackendId;

    @JoinColumn(name = "skill_name")
    @OneToOne(fetch = FetchType.LAZY)
    private Image image;

    @OneToMany(mappedBy = "skillsBackend", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserPortfolioSkillsBackend> userPortfolioSkillsBackendList = new ArrayList<>();

    @Builder
    public SkillsBackend(Long skillsBackendId, Image image){
        this.skillsBackendId = skillsBackendId;
        this.image = image;
    }

}
