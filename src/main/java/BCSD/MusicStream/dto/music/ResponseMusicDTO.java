package BCSD.MusicStream.dto.music;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Time;

@Getter
@Builder
@ToString
public class ResponseMusicDTO {
    private Integer id;
    private String name;
    private String singerName;
    private Time duration;
    private String imageFilePath;
    private String soundFilePath;
}
