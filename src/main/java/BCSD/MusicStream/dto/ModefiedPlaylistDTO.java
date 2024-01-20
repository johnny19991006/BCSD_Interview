package BCSD.MusicStream.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ModefiedPlaylistDTO {
    private Integer playlist_id;
    private String playlist_name;
}
