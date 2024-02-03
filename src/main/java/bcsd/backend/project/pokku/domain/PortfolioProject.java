package bcsd.backend.project.pokku.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "portfolio_project")
@NoArgsConstructor
public class PortfolioProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_project_id")
    private Long portfolioProjectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_explanation")
    private String projectExplanation;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserInfo userInfo;

    @Builder
    public PortfolioProject(Long portfolioProjectId, String projectName, String projectExplanation, UserInfo userInfo){
        this.portfolioProjectId = portfolioProjectId;
        this.projectExplanation = projectExplanation;
        this.projectName = projectName;
        this.userInfo = userInfo;
    }

}
