package HSAnimal.demo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserKeywordsDto {
    private String userId;
    private int optionId;
}
