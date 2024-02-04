package bcsd.backend.project.pokku.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "image")
@Getter
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

    @OneToOne(mappedBy = "image", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private SkillsDeployment skillsDeployment;

    @OneToOne(mappedBy = "image", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private SkillsVersioncontrol skillsVersioncontrol;

    @OneToOne(mappedBy = "image", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private SkillsCommunication skillsCommunication;

    @OneToOne(mappedBy = "image", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private SkillsCertification skillsCertification;

    @Builder
    public Image(String skillName, String imageUrl){
        this.skillName = skillName;
        this.imageUrl = imageUrl;
    }
}
