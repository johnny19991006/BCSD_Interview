package BCSD.MusicStream.exception;

import BCSD.MusicStream.domain.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER-404", "사용자를 찾을 수 없습니다."),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "CAT-404", "카테고리를 찾을 수 없습니다."),
    WEATHER_NOT_FOUND(HttpStatus.NOT_FOUND, "WEATHER-404", "날씨 정보를 찾을 수 없습니다."),
    AUTHORITY_NOT_FOUND(HttpStatus.NOT_FOUND, "WEATHER-404", "권한 정보를 찾을 수 없습니다."),
    LYRICS_NOT_FOUND(HttpStatus.NOT_FOUND, "WEATHER-404", "가사 정보를 찾을 수 없습니다."),
    MUSIC_NOT_FOUND(HttpStatus.NOT_FOUND, "MUSIC-404", "음악을 찾을 수 없습니다."),
    PLAYLIST_NOT_FOUND(HttpStatus.NOT_FOUND, "PLAYLIST-404", "플레이리스트를 찾을 수 없습니다."),
    SOUND_FILE_NOT_CONTAIN(HttpStatus.BAD_REQUEST, "MUSIC-SOUND-FILE-409", "음악파일을 첨부하지 않았습니다."),
    IMAGE_FILE_NOT_CONTAIN(HttpStatus.BAD_REQUEST, "MUSIC-IMAGE-FILE-409", "이미지파일을 첨부하지 않았습니다."),
    NAME_BLANK(HttpStatus.BAD_REQUEST, "MUSIC-NAME-409", "노래제목이 공백입니다."),
    SINGER_NAME_BLANK(HttpStatus.BAD_REQUEST, "SIGNER-NAME-409", "가수이름이 공백입니다."),
    LYRICS_BLANK(HttpStatus.BAD_REQUEST, "LYRICS-409", "가사가 공백입니다."),
    HAS_EMAIL(HttpStatus.BAD_REQUEST, "EMAIL-409", "존재하는 이메일입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "PWD-401", "비밀번호가 일치하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}