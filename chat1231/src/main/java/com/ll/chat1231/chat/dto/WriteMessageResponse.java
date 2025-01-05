package com.ll.chat1231.chat.dto;

import com.ll.chat1231.chat.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class WriteMessageResponse {
    private ChatMessage chatMessage;
}
