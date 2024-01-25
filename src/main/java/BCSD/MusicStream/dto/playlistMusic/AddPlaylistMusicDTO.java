package BCSD.MusicStream.dto.playlistMusic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class AddPlaylistMusicDTO {
    private Integer playlistId;
    private Integer musicId;
}
