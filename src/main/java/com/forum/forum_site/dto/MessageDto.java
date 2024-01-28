package com.forum.forum_site.dto;

import com.forum.forum_site.domain.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private String content;
    private String senderName;
    private String receiverName;

    public static MessageDto toDto(Message message) {
        return new MessageDto(
                message.getContent(),
                message.getSender().getUsername(),
                message.getReceiver().getUsername()
        );
    }
}