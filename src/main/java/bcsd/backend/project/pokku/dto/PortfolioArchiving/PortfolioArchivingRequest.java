package bcsd.backend.project.pokku.dto.PortfolioArchiving;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortfolioArchivingRequest {
    private String userId;
    private String archivingName;
    private String archivingExplanation;

}
