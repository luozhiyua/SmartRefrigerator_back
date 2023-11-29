package com.github.angel.raa.modules.controller;

import com.github.angel.raa.modules.dto.ChatDTO;
import com.github.angel.raa.modules.service.interfaces.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/ask-question")
    public ResponseEntity<ChatDTO> askQuestion(@Valid @RequestBody ChatDTO chatDTO) {
        return ResponseEntity.ok(chatService.generateAnswer(chatDTO));
    }
}
