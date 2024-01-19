package bcsd.backend.project.pokku.dto.Portfolio;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioResponse {
    private String userId;
    private List<Long> skillsFrontendIdList;
}
