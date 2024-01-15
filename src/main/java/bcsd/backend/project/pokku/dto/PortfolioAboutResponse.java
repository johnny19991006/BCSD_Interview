package bcsd.backend.project.pokku.dto;

import bcsd.backend.project.pokku.domain.PortfolioAbout;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioAboutResponse {
    private Boolean userNameVisible;
    private Boolean userTelVisible;
    private Boolean userEmailVisible;
    private Boolean userEducationVisible;

    public PortfolioAboutResponse(PortfolioAbout portfolioAbout){
        this.userNameVisible = portfolioAbout.getUserNameVisible();
        this.userEducationVisible = portfolioAbout.getUserEducationVisible();
        this.userEmailVisible = portfolioAbout.getUserEmailVisible();
        this.userTelVisible = portfolioAbout.getUserTelVisible();
    }
}
