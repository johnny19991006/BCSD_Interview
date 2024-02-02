package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.Like;

public interface LikeService {
    void like(Integer musicId, Integer memberId);
    Boolean deleteLikeAndDislike(Integer musicId, Integer memberId);
    void dislike(Integer musicId, Integer memberId);
}
