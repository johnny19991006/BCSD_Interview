package bcsd.backend.project.pokku.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "portfolio_career")
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioCareer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_career_id")
    private Long portfolioCareerId;

    @Column(name = "career_explanation")
    private String careerExplanation;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserInfo userInfo;


}
