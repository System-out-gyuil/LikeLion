package com.ll.mysql.domain.chatRoom.service;

import com.ll.mysql.domain.chatRoom.entity.ChatRoomEntity;
import com.ll.mysql.domain.chatRoom.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomEntity create(String name) {
        ChatRoomEntity chatRoom = ChatRoomEntity.builder()
                .name(name)
                .build();

        chatRoomRepository.save(chatRoom);

        return chatRoom;
    }

    public List<ChatRoomEntity> getAll() {
        return chatRoomRepository.findAll();
    }
}