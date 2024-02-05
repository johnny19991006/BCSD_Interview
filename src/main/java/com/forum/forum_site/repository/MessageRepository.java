package com.forum.forum_site.repository;

import com.forum.forum_site.domain.Message;
import com.forum.forum_site.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findAllByReceiver(User receiver);
    List<Message> findAllBySender(User sender);
}
