package bcsd.backend.project.pokku.dto.PortfolioAbout;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortfolioAboutRequest {
    private Boolean userNameVisible;
    private Boolean userTelVisible;
    private Boolean userEmailVisible;
    private Boolean userEducationVisible;
}
