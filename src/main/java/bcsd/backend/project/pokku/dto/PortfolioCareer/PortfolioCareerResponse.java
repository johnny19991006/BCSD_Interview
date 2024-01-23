package bcsd.backend.project.pokku.dto.PortfolioCareer;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioCareerResponse {
    private Long portfolioCareerId;
    private String careerExplanation;
}
