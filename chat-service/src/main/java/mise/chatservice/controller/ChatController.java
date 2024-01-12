package mise.chatservice.controller;
//import com.github.angel.raa.modules.service.impl.ChatServiceImpl;
import lombok.RequiredArgsConstructor;
import mise.chatservice.service.impl.ChatServiceImpl;
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

    @GetMapping("/test")
    public String test(){
        return "chat-service1";
    }

    @PostMapping("/ques")
    public String processString(@RequestBody String request) throws IOException {
        request = request.substring(13, request.length() -2);
        String processedString = qaService.Answer(request);
        System.out.println(processedString);
        return processedString;
    }
}