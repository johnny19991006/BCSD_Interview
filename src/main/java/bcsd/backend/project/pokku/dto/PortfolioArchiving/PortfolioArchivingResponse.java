package bcsd.backend.project.pokku.dto.PortfolioArchiving;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PortfolioArchivingResponse {
    private Long portfolioArchivingId;
    private String archivingName;
    private String archivingExplanation;

    public PortfolioArchivingResponse(Long portfolioArchivingId, String archivingName, String archivingExplanation){
        this.portfolioArchivingId = portfolioArchivingId;
        this.archivingName = archivingName;
        this.archivingExplanation = archivingExplanation;
    }
}
