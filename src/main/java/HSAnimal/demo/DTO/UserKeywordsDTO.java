package HSAnimal.demo.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserKeywordsDTO {
    private String userId;
    private int optionId;
}
