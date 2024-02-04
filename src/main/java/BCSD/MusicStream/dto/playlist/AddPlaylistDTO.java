package BCSD.MusicStream.dto.playlist;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddPlaylistDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;
}
