package com.ll.global.initData;

import com.ll.domain.chat.chatRoom.entity.ChatRoom;
import com.ll.domain.chat.chatRoom.service.ChatRoomService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

public class NotProd {

    @Bean
    public ApplicationRunner initNotProd(ChatRoomService chatRoomService) {
        return args -> {
            ChatRoom chatroom1 = chatRoomService.make("공부");
            ChatRoom chatroom2 = chatRoomService.make("식사");
            ChatRoom chatroom3 = chatRoomService.make("휴식");
        };

        System.out.println("Not Prod");

    }

}
