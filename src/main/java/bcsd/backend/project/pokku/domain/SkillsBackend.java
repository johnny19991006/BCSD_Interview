package bcsd.backend.project.pokku.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@Table(name = "skills_backend")
@AllArgsConstructor
@NoArgsConstructor
public class SkillsBackend {

    @Id
    @Column(name = "skills_backend_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillsBackendId;

    @JoinColumn(name = "skill_name")
    @OneToOne(fetch = FetchType.LAZY)
    private Image image;
}
