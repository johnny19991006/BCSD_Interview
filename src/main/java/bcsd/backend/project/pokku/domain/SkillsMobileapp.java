package bcsd.backend.project.pokku.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "skills_mobileapp")
@NoArgsConstructor
public class SkillsMobileapp {

    @Id
    @Column(name = "skills_mobileapp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillsMobileappId;

    @JoinColumn(name = "skill_name")
    @OneToOne(fetch = FetchType.LAZY)
    private Image image;

    @OneToMany(mappedBy = "skillsMobileapp", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserPortfolioSkillsMobileapp> userPortfolioSkillsMobileappList = new ArrayList<>();

    @Builder
    public SkillsMobileapp(Long skillsMobileappId, Image image){
        this.skillsMobileappId = skillsMobileappId;
        this.image = image;
    }

}
