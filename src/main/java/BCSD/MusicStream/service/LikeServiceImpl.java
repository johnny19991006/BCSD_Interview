package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.Like;
import BCSD.MusicStream.domain.Member;
import BCSD.MusicStream.domain.Music;
import BCSD.MusicStream.exception.CustomException;
import BCSD.MusicStream.exception.ErrorCode;
import BCSD.MusicStream.repository.LikeRepository;
import BCSD.MusicStream.repository.MemberRepository;
import BCSD.MusicStream.repository.MusicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{
    private final LikeRepository likeRepository;
    private final MusicRepository musicRepository;
    private final MemberRepository memberRepository;
    @Override
    public void like(Integer musicId, Integer memberId) {
        deleteLikeAndDislike(musicId, memberId);
        Music music = musicRepository.findById(musicId).orElseThrow(() -> new CustomException(ErrorCode.MUSIC_NOT_FOUND));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Like like = Like.builder()
                .isLike(true)
                .member(member)
                .music(music)
                .build();
        likeRepository.save(like);
    }

    @Override
    public Boolean deleteLikeAndDislike(Integer musicId, Integer memberId) {
        Optional<Like> like = likeRepository.findLikeByMusicIdAndMemberId(musicId, memberId);
        if(!like.isPresent()) return false;
        likeRepository.deleteById(like.get().getId());
        return true;
    }

    @Override
    public void dislike(Integer musicId, Integer memberId) {
        deleteLikeAndDislike(musicId, memberId);
        Music music = musicRepository.findById(musicId).orElseThrow(() -> new CustomException(ErrorCode.MUSIC_NOT_FOUND));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Like like = Like.builder()
                .isLike(false)
                .member(member)
                .music(music)
                .build();
        likeRepository.save(like);
    }
}
