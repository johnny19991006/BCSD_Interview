package bcsd.backend.project.pokku.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@Table(name = "skills_frontend")
@AllArgsConstructor
@NoArgsConstructor
public class SkillsFrontend {

    @Id
    @Column(name = "skills_frontend_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillsFontendId;

    @JoinColumn(name = "skill_name")
    @OneToOne(fetch = FetchType.LAZY)
    private Image image;
}
