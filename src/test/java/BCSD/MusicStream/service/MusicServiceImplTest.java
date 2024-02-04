package BCSD.MusicStream.service;

import BCSD.MusicStream.dto.music.ResponseMusicDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootTest
class MusicServiceImplTest {

    @Autowired
    private MusicService musicService;
    @Test
    void getMusicByCommend() {
        int page = 2; // 첫 번째 페이지
        int size = 10; // 페이지당 10개의 아이템
        Pageable pageable = PageRequest.of(page, size);
        List<ResponseMusicDTO> requestMusicDTOList = musicService.getAllMusic(1, pageable);
        for(ResponseMusicDTO requestMusicDTO: requestMusicDTOList) System.out.println(requestMusicDTO);
    }

    @Test
    void getAllMusicByWeather() {
        int page = 0; // 첫 번째 페이지
        int size = 10; // 페이지당 10개의 아이템
        Pageable pageable = PageRequest.of(page, size);
        List<ResponseMusicDTO> requestMusicDTOList = musicService.getAllMusicByWeather(1, "snow", pageable);
        for(ResponseMusicDTO requestMusicDTO: requestMusicDTOList) System.out.println(requestMusicDTO);
    }
}