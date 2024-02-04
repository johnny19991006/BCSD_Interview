package com.forum.forum_site.service.message;

import com.forum.forum_site.domain.*;
import com.forum.forum_site.dto.message.MessageDto;
import com.forum.forum_site.exception.MessageException;
import com.forum.forum_site.repository.MessageRepository;
import com.forum.forum_site.repository.UserRepository;

import com.forum.forum_site.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


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

        Message message = messageDto.toEntity(currentUser, receiver);
        messageRepository.save(message);

    }

    @Transactional(readOnly = true)
    @Override
    public List<MessageDto> receivedMessageRoom() {
        return messageRepository.findAllByReceiver(getCurrentAuthenticatedUser()).stream()
                .filter(message -> !message.isDeletedByReceiver())
                .map(message -> new MessageDto(message.getContent()))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteMesssageByReceiver(int messageId) {
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
        return messageRepository.findAllBySender(getCurrentAuthenticatedUser()).stream()
                .filter(message -> !message.isDeletedBySender())
                .map(message -> new MessageDto(message.getContent()))
                .collect(Collectors.toList());
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
