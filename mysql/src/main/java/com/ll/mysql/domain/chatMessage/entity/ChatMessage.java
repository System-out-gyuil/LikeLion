package com.ll.mysql.domain.chatMessage.entity;

import com.ll.mysql.domain.chatRoom.entity.ChatRoom;
import com.ll.mysql.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@Getter
@SuperBuilder
@ToString(callSuper = true)
public class ChatMessage extends BaseEntity {
    public ChatMessage() {
    }

    private String writerName;
    private String message;

    @ManyToOne
    private ChatRoom chatName;

}