package com.forum.forum_site.controller;

import com.forum.forum_site.dto.MessageDto;
import com.forum.forum_site.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;


    // 쪽지 보내기
    @PostMapping("/{receiverId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendMessage(@PathVariable("receiverId") Integer receiverId, @RequestBody MessageDto messageDto) {
        messageService.write(messageDto, receiverId);
    }

    // 받은 쪽지함 읽기
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/received")
    public ResponseEntity<List<MessageDto>> getReceivedMessage() {
        return ResponseEntity.ok(messageService.receivedMessageRoom());
    }

    // 받은 쪽지 삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/received/{id}")
    public void deleteReceivedMessage(@PathVariable("id") Integer id) {
        messageService.deleteMesssageByReceiver(id);
    }

    // 보낸 쪽지함 읽기
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/sentRoom")
    public ResponseEntity<List<MessageDto>> getSentMessage() {
        return ResponseEntity.ok(messageService.sentMessageRoom());
    }

    // 보낸 쪽지 삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/sent/{id}")
    public void deleteSentMessage(@PathVariable("id") Integer id) {
        messageService.deleteMessageBySender(id);
    }

}
