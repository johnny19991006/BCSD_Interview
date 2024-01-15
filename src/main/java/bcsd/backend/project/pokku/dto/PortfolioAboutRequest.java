package bcsd.backend.project.pokku.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortfolioAboutRequest {
    private String userId;
    private Boolean userNameVisible;
    private Boolean userTelVisible;
    private Boolean userEmailVisible;
    private Boolean userEducationVisible;
}
