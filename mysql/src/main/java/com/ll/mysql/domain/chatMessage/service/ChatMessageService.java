package com.ll.mysql.domain.chatMessage.service;

import com.ll.mysql.domain.chatMessage.entity.ChatMessageEntity;
import com.ll.mysql.domain.chatMessage.repository.ChatMessageRepository;
import com.ll.mysql.domain.chatRoom.entity.ChatRoomEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    public void create(ChatRoomEntity chatRoomEntity, String writerName, String content) {
        ChatMessageEntity chatMessageEntity = ChatMessageEntity.builder()
                .chatName(chatRoomEntity)
                .writerName(writerName)
                .message(content)
                .build();
        chatMessageRepository.save(chatMessageEntity);
    }
}