package BCSD.MusicStream.dto.Lyrics;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class RequestLyricsDTO {
    private String lyrics;
}
