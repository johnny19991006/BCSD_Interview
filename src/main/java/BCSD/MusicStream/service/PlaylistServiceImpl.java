package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.Playlist;
import BCSD.MusicStream.domain.Member;
import BCSD.MusicStream.dto.playlist.AddPlaylistDTO;
import BCSD.MusicStream.dto.playlist.ModifyPlaylistDTO;
import BCSD.MusicStream.dto.playlist.RequestPlaylistDTO;
import BCSD.MusicStream.repository.MusicRepository;
import BCSD.MusicStream.repository.PlayListRepository;
import BCSD.MusicStream.repository.PlaylistMusicRepository;
import BCSD.MusicStream.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService{
    private final MusicRepository musicRepository;
    private final PlaylistMusicRepository playlistMusicRepository;
    private final PlayListRepository playListRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<RequestPlaylistDTO> getPlaylistByMemberId(Integer memberId) {
        List<Playlist> playlists = playListRepository.findAllByMemberId(memberId);
        List<RequestPlaylistDTO> requestPlaylistDTOS = new ArrayList<>(playlists.size());
        for(Playlist playlist: playlists) {
            requestPlaylistDTOS.add(RequestPlaylistDTO.builder()
                    .id(playlist.getId())
                    .name(playlist.getName())
                    .build());
        }
        return requestPlaylistDTOS;
    }

    @Override
    public void addPlaylist(AddPlaylistDTO addPlaylistDTO, Integer memberId) {
        Member member = memberRepository.findById(memberId.longValue()).orElseThrow(() -> new EntityNotFoundException("member not found with id " + memberId));
        playListRepository.save(Playlist.builder()
                .name(addPlaylistDTO.getName())
                .member(member)
                .build());
    }

    @Override
    public void removePlaylist(Integer playlistId) {
        // 키와 관련된 다른 테이블의 데이터도 같이 삭제해주기
        playListRepository.deleteById(playlistId.longValue());
    }

    @Override
    public void modifyPlaylistName(ModifyPlaylistDTO modifyPlaylistDTO) {
        Playlist playlist = playListRepository.findById(modifyPlaylistDTO.getId().longValue()).orElseThrow(() -> new EntityNotFoundException("Entity not found with id " + modifyPlaylistDTO.getId()));
        playlist.setName(modifyPlaylistDTO.getName());
        playListRepository.save(playlist);
    }
}
