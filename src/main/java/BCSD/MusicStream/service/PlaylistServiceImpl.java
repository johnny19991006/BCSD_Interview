package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.Playlist;
import BCSD.MusicStream.domain.Member;
import BCSD.MusicStream.dto.playlist.AddPlaylistDTO;
import BCSD.MusicStream.dto.playlist.ModifyPlaylistDTO;
import BCSD.MusicStream.dto.playlist.ResponsePlaylistDTO;
import BCSD.MusicStream.exception.CustomException;
import BCSD.MusicStream.exception.ErrorCode;
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
    public List<ResponsePlaylistDTO> getPlaylistByMemberId(Integer memberId) {
        List<Playlist> playlists = playListRepository.findAllByMemberId(memberId);
        List<ResponsePlaylistDTO> requestPlaylistDTOS = new ArrayList<>(playlists.size());
        for(Playlist playlist: playlists) {
            requestPlaylistDTOS.add(ResponsePlaylistDTO.builder()
                    .id(playlist.getId())
                    .name(playlist.getName())
                    .build());
        }
        return requestPlaylistDTOS;
    }

    @Override
    public ResponsePlaylistDTO addPlaylist(AddPlaylistDTO addPlaylistDTO, Integer memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Playlist playlist = playListRepository.save(Playlist.builder()
                .name(addPlaylistDTO.getName())
                .member(member)
                .build());
        return ResponsePlaylistDTO.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .build();
    }

    @Override
    public void removePlaylist(Integer playlistId) {
        playListRepository.deleteById(playlistId);
    }

    @Override
    public ResponsePlaylistDTO modifyPlaylistName(ModifyPlaylistDTO modifyPlaylistDTO) {
        Playlist playlist = playListRepository.findById(modifyPlaylistDTO.getId()).orElseThrow(() -> new CustomException(ErrorCode.PLAYLIST_NOT_FOUND));
        playlist.setName(modifyPlaylistDTO.getName());
        playlist = playListRepository.save(playlist);
        return ResponsePlaylistDTO.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .build();
    }
}
