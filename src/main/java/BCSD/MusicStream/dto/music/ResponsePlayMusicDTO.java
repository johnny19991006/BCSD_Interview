package BCSD.MusicStream.dto.music;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Time;

@Getter
@Builder
@ToString
public class ResponsePlayMusicDTO {
    private String lyrics;
    private Boolean isLike;
}
