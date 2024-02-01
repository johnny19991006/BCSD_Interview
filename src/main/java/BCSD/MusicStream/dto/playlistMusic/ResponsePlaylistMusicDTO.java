package BCSD.MusicStream.dto.playlistMusic;

import lombok.Builder;
import lombok.Getter;

import java.sql.Time;

@Getter
@Builder
public class ResponsePlaylistMusicDTO {
    private Integer id;
    private Integer musicId;
    private String musicName;
    private String singerName;
    private Time duration;
    private String imageFilePath;
    private String soundFilePath;
}
