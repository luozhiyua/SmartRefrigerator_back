package com.github.angel.raa.modules.controller;

import com.github.angel.raa.modules.dto.ChatDTO;
import com.github.angel.raa.modules.service.interfaces.ChatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/ask-question")
    public ResponseEntity<ChatDTO> askQuestion(@Valid @RequestBody ChatDTO chatDTO) {
        ChatDTO response = chatService.generateAnswer(chatDTO);
        return ResponseEntity.ok(response);
    }
}
