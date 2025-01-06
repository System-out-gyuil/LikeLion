package com.ll.mysql.domain.chatRoom.entity;

import com.ll.mysql.domain.chatMessage.entity.ChatMessageEntity;
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
public class ChatRoomEntity  extends BaseEntity {

    public ChatRoomEntity() {
    }

    private String name;

    @OneToMany
    private List<ChatMessageEntity> chatMessageEntities;

}
