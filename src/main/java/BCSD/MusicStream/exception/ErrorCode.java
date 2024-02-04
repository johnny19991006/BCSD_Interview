package BCSD.MusicStream.exception;

import BCSD.MusicStream.domain.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_AUTHORITY_ID(HttpStatus.BAD_REQUEST, "AUTHORITY-400", "유효하지 않은 권한ID."),
    AUTHORITY_NOT_FOUND(HttpStatus.NOT_FOUND, "AUTHORITY-404", "권한을 찾을 수 없습니다."),

    INVALID_MEMBER_ID(HttpStatus.BAD_REQUEST, "MEMBER-400", "유효하지 않은 멤버ID."),
    PERMISSION_DENIED_MEMBER(HttpStatus.FORBIDDEN, "MEMBER-403", "해당 멤버는 권한이 없습니다."),
    INVALID_MEMBER_EMAIL(HttpStatus.BAD_REQUEST, "MEMBER-400", "유효하지 않은 멤버EMAIL."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER-404", "사용자를 찾을 수 없습니다."),

    INVALID_CATEGORY_ID(HttpStatus.BAD_REQUEST, "CAT-400", "유효하지 않은 카테고리ID."),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "CAT-404", "카테고리를 찾을 수 없습니다."),

    INVALID_WEATHER_ID(HttpStatus.BAD_REQUEST, "WEATHER-400", "유효하지 않은 날씨ID."),
    WEATHER_NOT_FOUND(HttpStatus.NOT_FOUND, "WEATHER-404", "날씨를 찾을 수 없습니다."),

    FILE_FAILD(HttpStatus.SERVICE_UNAVAILABLE, "FILE-503", "파일저장 실패"),

    INVALID_LYRICS_ID(HttpStatus.BAD_REQUEST, "LYRICS-400", "유효하지 않은 가사ID."),
    LYRICS_NOT_FOUND(HttpStatus.NOT_FOUND, "LYRICS-404", "가사를 찾을 수 없습니다."),

    INVALID_MUSIC_ID(HttpStatus.BAD_REQUEST, "MUSIC-400", "유효하지 않은 음악ID."),
    PERMISSION_DENIED_MUSIC(HttpStatus.FORBIDDEN, "MUSIC-403", "해당 음악을 삭제할 권한이 없습니다."),
    MUSIC_NOT_FOUND(HttpStatus.NOT_FOUND, "MUSIC-404", "음악을 찾을 수 없습니다."),

    INVALID_PLAYLIST_ID(HttpStatus.BAD_REQUEST, "PLAYLIST-400", "유효하지 않은 플레이리스트ID."),
    PLAYLIST_NOT_FOUND(HttpStatus.NOT_FOUND, "PLAYLIST-404", "플레이리스트를 찾을 수 없습니다."),

    INVALID_BIRTH_DATE(HttpStatus.BAD_REQUEST, "BIRTH-400", "생년월일 형식이 잘못됐습니다."),


    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "PWD-401", "비밀번호가 일치하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}