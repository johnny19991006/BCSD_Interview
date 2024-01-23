package bcsd.backend.project.pokku.dto.PortfolioArchiving;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioArchivingResponse {
    private Long portfolioArchivingId;
    private String archivingName;
    private String archivingExplanation;
}
