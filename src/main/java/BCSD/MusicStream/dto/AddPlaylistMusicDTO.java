package BCSD.MusicStream.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AddPlaylistMusicDTO {
    private Integer playlist_id;
    private Integer music_id;
}
