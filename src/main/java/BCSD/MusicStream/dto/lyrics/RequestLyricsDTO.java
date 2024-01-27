package BCSD.MusicStream.dto.lyrics;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestLyricsDTO {
    private String lyrics;
}
