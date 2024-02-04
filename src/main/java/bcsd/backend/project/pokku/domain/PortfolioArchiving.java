package bcsd.backend.project.pokku.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "portfolio_archiving")
@NoArgsConstructor
public class PortfolioArchiving {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_archiving_id")
    private Long portfolioArchivingId;

    @Column(name = "archiving_name")
    private String archivingName;

    @Column(name = "archiving_explanation")
    private String archivingExplanation;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserInfo userInfo;

    @Builder
    public PortfolioArchiving(Long portfolioArchivingId, String archivingExplanation, String archivingName, UserInfo userInfo){
        this.archivingExplanation = archivingExplanation;
        this.portfolioArchivingId = portfolioArchivingId;
        this.archivingName = archivingName;
        this.userInfo = userInfo;
    }

}
