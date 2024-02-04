package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.Like;
import BCSD.MusicStream.domain.Member;
import BCSD.MusicStream.domain.Music;
import BCSD.MusicStream.exception.CustomErrorCodeException;
import BCSD.MusicStream.exception.ErrorCode;
import BCSD.MusicStream.repository.LikeRepository;
import BCSD.MusicStream.repository.MemberRepository;
import BCSD.MusicStream.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{
    private final LikeRepository likeRepository;
    private final MusicRepository musicRepository;
    private final MemberRepository memberRepository;
    @Transactional
    @Override
    public void like(Integer musicId, Integer memberId) {
        deleteLikeAndDislike(musicId, memberId);
        Music music = musicRepository.findById(musicId).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.MUSIC_NOT_FOUND));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.MEMBER_NOT_FOUND));
        likeRepository.save(Like.builder()
                .isLike(true)
                .member(member)
                .music(music)
                .build());
    }
    @Transactional
    @Override
    public Boolean deleteLikeAndDislike(Integer musicId, Integer memberId) {
        Optional<Like> like = likeRepository.findLikeByMusicIdAndMemberId(musicId, memberId);
        if(!like.isPresent()) return false;
        likeRepository.deleteById(like.get().getId());
        return true;
    }
    @Transactional
    @Override
    public void dislike(Integer musicId, Integer memberId) {
        deleteLikeAndDislike(musicId, memberId);
        Music music = musicRepository.findById(musicId).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.MUSIC_NOT_FOUND));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.MEMBER_NOT_FOUND));
        likeRepository.save(Like.builder()
                .isLike(false)
                .member(member)
                .music(music)
                .build());
    }
}
