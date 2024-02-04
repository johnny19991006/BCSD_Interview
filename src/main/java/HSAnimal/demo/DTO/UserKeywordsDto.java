package HSAnimal.demo.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserKeywordsDto {
    private String userId;
    private int optionId;
}
