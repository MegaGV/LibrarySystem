package xzz.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xzz.library.dto.MessageInfoDto;
import xzz.library.dto.MessageListDto;
import xzz.library.service.MessageService;

@Controller
@RequestMapping("/message")
@CrossOrigin(allowCredentials = "true")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/getMessages")
    @ResponseBody
    public MessageListDto getMessages(String userId){
        return messageService.getMessages(userId);
    }

    @GetMapping("/getMessage")
    @ResponseBody
    public MessageInfoDto getMessage(String userId, String messageId){
        return messageService.getMessage(userId, messageId);
    }
}
