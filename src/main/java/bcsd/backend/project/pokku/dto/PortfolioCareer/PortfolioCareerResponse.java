package bcsd.backend.project.pokku.dto.PortfolioCareer;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PortfolioCareerResponse {
    private Long portfolioCareerId;
    private String careerExplanation;

    public PortfolioCareerResponse(Long portfolioCareerId, String careerExplanation){
        this.portfolioCareerId = portfolioCareerId;
        this.careerExplanation = careerExplanation;
    }
}
