package com.ll.mysql.domain.chatRoom.entity;

import com.ll.mysql.domain.chatMessage.entity.ChatMessage;
import com.ll.mysql.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Setter
@Getter
@SuperBuilder
@ToString(callSuper = true)
public class ChatRoom extends BaseEntity {

    public ChatRoom() {
    }

    private String name;

    @OneToMany
    private List<ChatMessage> chatMessageEntities;

}
