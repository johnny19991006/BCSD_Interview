package BCSD.MusicStream.dto.playlist;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestPlaylistDTO {
    private Integer id;
    private String name;
}
