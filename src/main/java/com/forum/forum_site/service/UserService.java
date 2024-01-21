package com.forum.forum_site.service;

import com.forum.forum_site.domain.User;

import java.util.List;

public interface UserService {

    User loadUserByUsername(String username);

    // 모든 유저 조회
    List<User> findAll();
    // 특정 유저 조회
    User findById(Integer id);
    // 유저 생성
    User createUser(User newUser);
    // 유저 수정
    void updateUsername(Integer id, User user);
    // 유저 삭제
    void deleteUser(Integer id);



}
