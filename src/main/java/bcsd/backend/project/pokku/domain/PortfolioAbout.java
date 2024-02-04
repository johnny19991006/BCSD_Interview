package bcsd.backend.project.pokku.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "portfolio_about")
@Getter
@NoArgsConstructor
public class PortfolioAbout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_about_id")
    private Long portfolioAboutId;

    @Column(name = "user_name_visible")
    private Boolean userNameVisible;

    @Column(name = "user_tel_visible")
    private Boolean userTelVisible;

    @Column(name = "user_email_Visible")
    private Boolean userEmailVisible;

    @Column(name = "user_education_visible")
    private Boolean userEducationVisible;

    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserInfo userInfo;

    @Builder
    public PortfolioAbout(Boolean userEducationVisible, Boolean userEmailVisible, Boolean userNameVisible, Boolean userTelVisible, UserInfo userInfo){
        this.userEducationVisible = userEducationVisible;
        this.userNameVisible = userNameVisible;
        this.userEmailVisible = userEmailVisible;
        this.userTelVisible = userTelVisible;
        this.userInfo = userInfo;
    }
}
