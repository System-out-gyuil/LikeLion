package com.ll.chat1231.chat;

import com.ll.chat1231.chat.dto.MessagesRequest;
import com.ll.chat1231.chat.dto.MessagesResponse;
import com.ll.chat1231.chat.dto.WriteMessageRequest;
import com.ll.chat1231.chat.dto.WriteMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private List<ChatMessage> chatMessages = new ArrayList<>();
    private final SimpMessagingTemplate messagingTemplate;


    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<WriteMessageResponse> writeMessage(@RequestBody WriteMessageRequest writeMessageRequest) {

        ChatMessage cm = new ChatMessage(writeMessageRequest.getAuthorName(), writeMessageRequest.getContent());

        chatMessages.add(cm);

        messagingTemplate.convertAndSend("/topic/chat/writeMessage", new WriteMessageResponse(cm));

        return new RsData("200", "메세지가 작성되었습니다.", new WriteMessageResponse(cm));
    }

    @GetMapping("/messages")
    @ResponseBody
    public RsData<MessagesResponse> messages (MessagesRequest messagesRequest) {

        List<ChatMessage> messages = chatMessages;

        if(messagesRequest.fromId() != null) {

//            int index = -1;
//            for (int i = 0; i < messages.size(); i++) {
//                if (messages.get(i).getId() == messagesRequest.fromId()) {
//                    index = i;
//                    break;
//                }
//            }

            int index = IntStream.range(0, messages.size())
                    .filter(i -> chatMessages.get(i).getId() == messagesRequest.fromId())
                    .findFirst()
                    .orElse(-1);

            if (index != -1) {
                messages = messages.subList(index + 1, messages.size());
            }


        }

        return new RsData("200", "메세지 가져오기 성공", new MessagesResponse(messages, chatMessages.size()));
    }

    @GetMapping("/room")
    public String room () {
        return "chat/room";
    }
}
