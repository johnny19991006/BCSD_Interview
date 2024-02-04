package com.forum.forum_site.service.user;

import com.forum.forum_site.domain.Role;
import com.forum.forum_site.domain.User;
import com.forum.forum_site.dto.post.SimplePostInfo;

import java.util.List;
import java.util.Map;

public interface UserService {

    User loadUserByUsername(String username);

    // 모든 유저 조회
    List<User> findAll();
    // 특정 유저 조회
    User findById(Integer id);
    // 유저 생성
    User createUser(User newUser, Role role);
    // 유저 수정
    void updateUsername(Integer id, User user);
    // 유저 삭제
    void deleteUser(Integer id);

    void joinUser(Map<String, String> user);

    String loginUser(Map<String, String> user);

    List<SimplePostInfo> getUserScrapList();
}
