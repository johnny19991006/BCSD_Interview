package bcsd.backend.project.pokku.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@Table(name = "skills_versioncontrol")
@AllArgsConstructor
@NoArgsConstructor
public class SkillsVersioncontrol {

    @Id
    @Column(name = "skills_versioncontrol_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillsVersioncontrolId;

    @JoinColumn(name = "skill_name")
    @OneToOne(fetch = FetchType.LAZY)
    private Image image;
}
