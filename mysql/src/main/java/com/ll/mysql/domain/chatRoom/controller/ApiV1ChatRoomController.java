package com.ll.mysql.domain.chatRoom.controller;

import com.ll.mysql.domain.chatRoom.entity.ChatRoom;
import com.ll.mysql.domain.chatRoom.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat/rooms")
@RequiredArgsConstructor
@CrossOrigin(
        origins = "https://cdpn.io"
)
public class ApiV1ChatRoomController {
    private final ChatRoomService chatRoomService;

    @GetMapping
    public List<ChatRoom> getChatRooms() {
        List<ChatRoom> ChatRooms = chatRoomService.getAll();
        return ChatRooms;
    }

    @GetMapping("/api/v1/chat/rooms/{roomId}")
    public String getChatRoom(@PathVariable("roomId") Long roomId) {
        return roomId + "번 채팅방 조회완료";
    }

    @PostMapping
    public String createChatRoom(@RequestBody String name) {
        chatRoomService.create(name);
        return "채팅방 생성완료";
    }

}
