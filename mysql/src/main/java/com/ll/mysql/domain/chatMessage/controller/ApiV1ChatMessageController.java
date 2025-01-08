package com.ll.mysql.domain.chatMessage.controller;

import com.ll.mysql.domain.chatMessage.entity.ChatMessage;
import com.ll.mysql.domain.chatMessage.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat/messages")
@RequiredArgsConstructor
public class ApiV1ChatMessageController {

    private final ChatMessageService chatMessageService;

    @GetMapping
    public List<ChatMessage> messages() {
        List<ChatMessage> chatMessageEntities = chatMessageService.getAll();

        return chatMessageEntities;
    }

    @GetMapping("/{roomId}")
    public String showRoom(@PathVariable("roomId") Long roomId) {

        return roomId + "번 채팅방 조회 완료";
    }

}
