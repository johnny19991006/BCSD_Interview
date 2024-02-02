package HSAnimal.demo.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserKeywordsDTO {
    private String userId;
    private int optionId;
}
