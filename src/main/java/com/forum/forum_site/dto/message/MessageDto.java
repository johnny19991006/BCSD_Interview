package com.forum.forum_site.dto.message;

import com.forum.forum_site.domain.Message;
import com.forum.forum_site.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record MessageDto (String content){
    public Message toEntity(User sender, User receiver) {
        return Message.builder()
                .content(content)
                .sender(sender)
                .receiver(receiver)
                .deletedByReceiver(false)
                .deletedBySender(false)
                .build();
    }
}