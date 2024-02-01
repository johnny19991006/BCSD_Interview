package BCSD.MusicStream.service;

import BCSD.MusicStream.dto.playlist.AddPlaylistDTO;
import BCSD.MusicStream.dto.playlist.ModifyPlaylistDTO;
import BCSD.MusicStream.dto.playlist.ResponsePlaylistDTO;

import java.util.List;

public interface PlaylistService {
    public List<ResponsePlaylistDTO> getPlaylistByMemberId(Integer MemberId);
    public ResponsePlaylistDTO addPlaylist(AddPlaylistDTO addPlaylistDTO, Integer memberId);
    public void removePlaylist(Integer playlistId);
    public ResponsePlaylistDTO modifyPlaylistName(ModifyPlaylistDTO modifyPlaylistDTO);
}
