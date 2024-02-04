package bcsd.backend.project.pokku.dto.PortfolioArchiving;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PortfolioArchivingResponse {
    private Long portfolioArchivingId;
    private String archivingName;
    private String archivingExplanation;

}
