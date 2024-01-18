package bcsd.backend.project.pokku.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@Table(name = "skills_mobileapp")
@AllArgsConstructor
@NoArgsConstructor
public class SkillsMobileapp {

    @Id
    @Column(name = "skills_mobileapp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillsMobileappId;

    @JoinColumn(name = "skill_name")
    @OneToOne(fetch = FetchType.LAZY)
    private Image image;
}
