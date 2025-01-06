package com.ll.mysql.domain.chatMessage.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat/rooms")
public class ApiV1ChatMessageController {

    @GetMapping
    public String rooms() {
        return "채팅방 목록 조회 완료";
    }

    @GetMapping("/{roomId}")
    public String showRoom(@PathVariable("roomId") Long roomId) {
        return roomId + "번 채팅방 조회 완료";
    }

}
