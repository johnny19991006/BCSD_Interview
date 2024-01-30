package BCSD.MusicStream.service;

import BCSD.MusicStream.dto.music.RequestMusicDTO;
import BCSD.MusicStream.repository.MusicRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MusicServiceImplTest {

    @Autowired
    private MusicService musicService;
    @Test
    void getMusicByCommend() {
        int page = 2; // 첫 번째 페이지
        int size = 10; // 페이지당 10개의 아이템
        Pageable pageable = PageRequest.of(page, size);
        List<RequestMusicDTO> requestMusicDTOList = musicService.getAllMusic(1L, pageable);
        for(RequestMusicDTO requestMusicDTO: requestMusicDTOList) System.out.println(requestMusicDTO);
    }

    @Test
    void getAllMusicByWeather() {
        int page = 0; // 첫 번째 페이지
        int size = 10; // 페이지당 10개의 아이템
        Pageable pageable = PageRequest.of(page, size);
        List<RequestMusicDTO> requestMusicDTOList = musicService.getAllMusicByWeather(1L, "snow", pageable);
        for(RequestMusicDTO requestMusicDTO: requestMusicDTOList) System.out.println(requestMusicDTO);
    }
}