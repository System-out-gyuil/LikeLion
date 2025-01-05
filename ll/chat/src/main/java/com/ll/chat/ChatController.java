package com.ll.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @GetMapping("/room")
    @ResponseBody
    public String room() {
        return "room";
    }

    public record ChatWriteResponse(Long id) {
        
    }

    @PostMapping("/messages")
    @ResponseBody
    public RsData<ChatWriteResponse> messages() {
        List<ChatMessage> chatMessages = new ArrayList<>();

        ChatMessage message = new ChatMessage("test", "content");

        chatMessages.add(message);

        return new RsData(
                "200",
                "메세지 작성 완료",
                new ChatWriteResponse(message.getId())
        );
    }

    @GetMapping("/messages")
    @ResponseBody
    public RsData<List<ChatMessage>> messages () {

        ChatMessage message = new ChatMessage("test", "content");

        return new RsData<>("200", "성공", message);
    }
}
