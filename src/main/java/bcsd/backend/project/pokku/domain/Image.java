package bcsd.backend.project.pokku.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "image")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @Column(name = "skill_name")
    private String skillName;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToOne(mappedBy = "image", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private SkillsFrontend skillsFrontend;

    @OneToOne(mappedBy = "image", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private SkillsBackend skillsBackend;

    @OneToOne(mappedBy = "image", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private SkillsMobileapp skillsMobileapp;


}
