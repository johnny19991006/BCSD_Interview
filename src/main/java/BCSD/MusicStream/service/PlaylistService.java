package BCSD.MusicStream.service;

import BCSD.MusicStream.dto.AddPlaylistDTO;
import BCSD.MusicStream.dto.ModefiedPlaylistDTO;

public interface PlaylistService {
    public void addPlaylist(AddPlaylistDTO playlistDTO);
    public void removePlaylist(Integer playlistId);
    public void modefiedPlaylistName(ModefiedPlaylistDTO modefiedPlaylistDTO);
    public void addPlaylistMusic(Integer playlistId, Integer musicId);
}
