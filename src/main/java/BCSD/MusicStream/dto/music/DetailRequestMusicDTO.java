package BCSD.MusicStream.dto.music;

import BCSD.MusicStream.enums.LikeType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DetailRequestMusicDTO {
    private LikeType likeType;
    private String lyrics;
}
