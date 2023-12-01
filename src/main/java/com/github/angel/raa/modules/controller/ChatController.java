package com.github.angel.raa.modules.controller;
import com.github.angel.raa.modules.service.impl.ChatServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Validated
@RestController
@RequestMapping("/qa")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ChatController{
    @Autowired
    private ChatServiceImpl qaService;

    @PostMapping("/ques")
    public String processString(@RequestBody String request) throws IOException {
        String processedString = qaService.Answer(request);
        System.out.println(processedString);
        return processedString;
    }
}