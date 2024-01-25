package BCSD.MusicStream.service;

import BCSD.MusicStream.dto.playlist.AddPlaylistDTO;
import BCSD.MusicStream.dto.playlist.ModifyPlaylistDTO;
import BCSD.MusicStream.dto.playlist.RequestPlaylistDTO;

import java.util.List;

public interface PlaylistService {
    public List<RequestPlaylistDTO> getPlaylistByMemberId(Integer MemberId);
    public void addPlaylist(AddPlaylistDTO addPlaylistDTO, Integer memberId);
    public void removePlaylist(Integer playlistId);
    public void modifyPlaylistName(ModifyPlaylistDTO modifyPlaylistDTO);
}
