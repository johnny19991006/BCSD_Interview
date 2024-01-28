package com.forum.forum_site.service;

import com.forum.forum_site.domain.*;
import com.forum.forum_site.dto.MessageDto;
import com.forum.forum_site.exception.MessageException;
import com.forum.forum_site.repository.MessageRepository;
import com.forum.forum_site.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MessageServiceImpl extends BaseService implements MessageService{
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void write(MessageDto messageDto, int receiverId) {
        User currentUser = getCurrentAuthenticatedUser();
        User receiver = userRepository.findById(receiverId).orElseThrow(() -> new MessageException(MessageException.Type.RECEIVER_NOT_FOUND));

        Message message = new Message();
        message.setReceiver(receiver);
        message.setSender(currentUser);

        message.setContent(messageDto.getContent());
        message.setDeletedByReceiver(false);
        message.setDeletedBySender(false);
        messageRepository.save(message);

    }

    @Transactional(readOnly = true)
    @Override
    public List<MessageDto> receivedMessageRoom() {
        List<Message> messages = messageRepository.findAllByReceiver(getCurrentAuthenticatedUser());
        List<MessageDto> messageDtos = new ArrayList<>();

        for(Message message : messages) {
            // message에서 받은 편지함에서 삭제안했으면 보낼때 추가해서 보냄
            if(!message.isDeletedByReceiver()) {
                messageDtos.add(MessageDto.toDto(message));
            }
        }
        return messageDtos;
    }

    @Transactional
    @Override
    public void deleteMesssageByReceiver(int messageId) {
        User currentUser = getCurrentAuthenticatedUser();
        Message message = messageRepository.findById(messageId).orElseThrow(() ->
                new MessageException(MessageException.Type.MESSAGE_NOT_FOUND));

        message.deleteByReceiver(); // 받은 사람 메시지 삭제
        if (message.isDeleted()) {
            // 받은 사람 보낸 사람 모두 삭제시 데이터베이스에서 삭제
            messageRepository.delete(message);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<MessageDto> sentMessageRoom() {
        User currentUser = getCurrentAuthenticatedUser();
        List<Message> messages = messageRepository.findAllBySender(currentUser);
        List<MessageDto> messageDtos = new ArrayList<>();

        for(Message message : messages) {
            // message 에서 받은 편지함에서 삭제하지 않았으면 보낼 때 추가해서 보내줌
            if(!message.isDeletedBySender()) {
                messageDtos.add(MessageDto.toDto(message));
            }
        }
        return messageDtos;
    }

    @Override
    public void deleteMessageBySender(int messageId) {
        Message message = messageRepository.findById(messageId).orElseThrow(() ->
                new MessageException(MessageException.Type.MESSAGE_NOT_FOUND));

        message.deleteBySender();
        if (message.isDeleted()) {
            // 양쪽 다 삭제하면 데이터베이스에서 삭제
            messageRepository.delete(message);
        }
    }
}
