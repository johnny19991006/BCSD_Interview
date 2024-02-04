package bcsd.backend.project.pokku.dto.PortfolioAbout;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PortfolioAboutResponse {
    private Boolean userNameVisible;
    private Boolean userTelVisible;
    private Boolean userEmailVisible;
    private Boolean userEducationVisible;

}
