package com.forum.forum_site.service;

import com.forum.forum_site.domain.Message;
import com.forum.forum_site.domain.User;
import com.forum.forum_site.dto.MessageDto;

import java.util.List;
import java.util.Objects;

public interface MessageService {

    // 편지 쓰기
    void write(MessageDto messageDto, int receiverId);

    // 받은 편지함 불러오기
    List<MessageDto> receivedMessageRoom();

    // 받은 편지 삭제하기
    void deleteMesssageByReceiver(int messageId);

    // 보낸 편지함 불러오기
    List<MessageDto> sentMessageRoom();

    // 보낸 편지 삭제
    void deleteMessageBySender(int messageId);
}
