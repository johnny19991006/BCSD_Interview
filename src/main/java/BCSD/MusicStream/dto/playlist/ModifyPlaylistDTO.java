package BCSD.MusicStream.dto.playlist;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ModifyPlaylistDTO {
    private Integer id;
    private String name;
}
