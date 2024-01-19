package bcsd.backend.project.pokku.dto.Portfolio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortfolioRequest {
    private String userId;
    private Long skillsFrontendId;
}
