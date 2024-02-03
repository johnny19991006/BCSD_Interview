package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.Playlist;
import BCSD.MusicStream.domain.Member;
import BCSD.MusicStream.dto.playlist.AddPlaylistDTO;
import BCSD.MusicStream.dto.playlist.ModifyPlaylistDTO;
import BCSD.MusicStream.dto.playlist.ResponsePlaylistDTO;
import BCSD.MusicStream.exception.CustomErrorCodeException;
import BCSD.MusicStream.exception.ErrorCode;
import BCSD.MusicStream.repository.PlayListRepository;
import BCSD.MusicStream.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService{
    private final PlayListRepository playListRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<ResponsePlaylistDTO> getPlaylistByMemberId(Integer memberId, Pageable pageable) {
        List<Playlist> playlists = playListRepository.findAllByMemberId(memberId, pageable);
        return playlists.stream().map(playlist -> ResponsePlaylistDTO.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .build()).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ResponsePlaylistDTO addPlaylist(AddPlaylistDTO addPlaylistDTO, Integer memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.MEMBER_NOT_FOUND));
        Playlist playlist = playListRepository.save(Playlist.builder()
                .name(addPlaylistDTO.getName())
                .member(member)
                .build());
        return ResponsePlaylistDTO.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .build();
    }
    @Transactional
    @Override
    public void removePlaylist(Integer playlistId) {
        playListRepository.deleteById(playlistId);
    }

    @Transactional
    @Override
    public ResponsePlaylistDTO modifyPlaylistName(ModifyPlaylistDTO modifyPlaylistDTO) {
        Playlist playlist = playListRepository.findById(modifyPlaylistDTO.getId()).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.PLAYLIST_NOT_FOUND));
        playlist.setName(modifyPlaylistDTO.getName());
        return ResponsePlaylistDTO.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .build();
    }
}
