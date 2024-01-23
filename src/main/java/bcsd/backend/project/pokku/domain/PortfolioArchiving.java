package bcsd.backend.project.pokku.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "portfolio_archiving")
@AllArgsConstructor
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


}
