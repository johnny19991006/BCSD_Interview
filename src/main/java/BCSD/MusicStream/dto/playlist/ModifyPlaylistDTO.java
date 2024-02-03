package BCSD.MusicStream.dto.playlist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ModifyPlaylistDTO {
    @NotNull(message = "Id cannot be null")
    private Integer id;

    @NotBlank(message = "Name cannot be blank")
    private String name;
}
