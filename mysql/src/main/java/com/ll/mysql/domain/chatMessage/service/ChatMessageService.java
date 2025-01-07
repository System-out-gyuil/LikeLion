package com.ll.mysql.domain.chatMessage.service;

import com.ll.mysql.domain.chatMessage.entity.ChatMessage;
import com.ll.mysql.domain.chatMessage.repository.ChatMessageRepository;
import com.ll.mysql.domain.chatRoom.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService{

    private final ChatMessageRepository chatMessageRepository;

    public void create(ChatRoom chatRoom, String writerName, String content) {
        ChatMessage chatMessage = ChatMessage.builder()
                .chatName(chatRoom)
                .writerName(writerName)
                .message(content)
                .build();

        chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> getAll() {
        List<ChatMessage> chatMessageEntities = chatMessageRepository.findAll();
        return chatMessageEntities;
    }
}