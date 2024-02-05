package com.forum.forum_site.service.message;

import com.forum.forum_site.dto.message.MessageDto;

import java.util.List;

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
