package com.github.angel.raa.modules.service.interfaces;

import com.github.angel.raa.modules.dto.ChatDTO;

public interface ChatService {
    ChatDTO generateAnswer(ChatDTO chatDTO);
}
