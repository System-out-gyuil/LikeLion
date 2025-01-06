package com.ll.mysql.global.initData;

import com.ll.mysql.domain.chatMessage.service.ChatMessageService;
import com.ll.mysql.domain.chatRoom.entity.ChatRoomEntity;
import com.ll.mysql.domain.chatRoom.service.ChatRoomService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.stream.IntStream;

@Configuration
@Profile("!prod")
public class NotProd {
    @Bean
    public ApplicationRunner applicationRunner(ChatRoomService chatRoomService, ChatMessageService chatMessageService) {
        return args -> {
            ChatRoomEntity chatRoom1 = chatRoomService.create("room1");
            ChatRoomEntity chatRoom2 = chatRoomService.create("room2");
            ChatRoomEntity chatRoom3 = chatRoomService.create("room3");
            IntStream.rangeClosed(1, 100).forEach(num -> {
                chatMessageService.create(chatRoom1, "홍길동", "채팅메세지" + num);
            });
            System.out.println("This is not a production environment.");
        };
    }
}