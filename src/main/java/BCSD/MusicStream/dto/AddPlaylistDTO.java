package BCSD.MusicStream.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AddPlaylistDTO {
    private String playlist_name;
    private Integer user_id;
}
