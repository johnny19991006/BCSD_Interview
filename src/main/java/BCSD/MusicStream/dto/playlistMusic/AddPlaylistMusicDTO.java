package BCSD.MusicStream.dto.playlistMusic;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class AddPlaylistMusicDTO {
    @NotNull(message = "PlaylistId cannot be null")
    private Integer playlistId;

    @NotNull(message = "MusicId cannot be null")
    private Integer musicId;
}
