package com.example.board.service;

import com.example.board.domain.Hashtag;
import com.example.board.repository.HashtagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public class HashtagServiceImpl implements HashtagService{
    private final HashtagRepository hashtagRepository;
    @Autowired
    public HashtagServiceImpl(HashtagRepository repository) {
        this.hashtagRepository = repository;
    }
    @Override
    public Hashtag insertHashtag(Hashtag hashtag) throws SQLException { // 해시태그 추가
        return hashtagRepository.save(hashtag);
    }
    @Override
    public List<Hashtag> getAllHashtags() throws SQLException { // 해시태그 전체조회 (id 오름차순)
        return hashtagRepository.findAllByOrderByHashtagIdAsc();
    }
    @Override
    public void deleteHashtag(Integer hashtagId) throws SQLException { // 해시태그 삭제
        hashtagRepository.deleteById(hashtagId);
    }
    
}
