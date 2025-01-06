package com.ll.mysql.domain.chatMessage.entity;

import com.ll.mysql.domain.chatRoom.entity.ChatRoomEntity;
import com.ll.mysql.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
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
public class ChatMessageEntity extends BaseEntity {
    public ChatMessageEntity() {
    }

    private String writerName;
    private String message;

    @ManyToOne
    private ChatRoomEntity chatName;

}