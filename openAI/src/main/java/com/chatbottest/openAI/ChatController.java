package com.chatbottest.openAI;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/v1/chat")
@RestController
public class ChatController {
    private final OpenAiChatModel openAiChatModel;

    public ChatController(OpenAiChatModel openAiChatModel) {
        this.openAiChatModel = openAiChatModel;
    }
    @GetMapping("/ai")
    public Map<String, String> chat(@RequestBody String message) {
        Map<String, String> responses =  new HashMap<>();
        String openAiResponse = openAiChatModel.call(message);
        responses.put("OpenAI - ChatGPT 3.5 응답", openAiResponse);

        return responses;
    }
}
