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

    @GetMapping("/getMessageList")
    @ResponseBody
    public MessageListDto getMessageList(String userId){
        return messageService.getMessageList(userId);
    }

    @GetMapping("/getMessage")
    @ResponseBody
    public MessageInfoDto getMessage(String userId, String messageId){
        return messageService.getMessage(userId, messageId);
    }

    @PostMapping("/setRead")
    @ResponseBody
    public String setRead(String userId, String messageId){
        return messageService.setStatus(userId, messageId, 1);
    }

    @PostMapping("/setUnRead")
    @ResponseBody
    public String setUnRead(String userId, String messageId){
        return messageService.setStatus(userId, messageId, 0);
    }

    @PostMapping("/deleteMessage")
    @ResponseBody
    public String deleteMessage(String userId, String messageId){
        return messageService.deleteMessage(userId, messageId);
    }
}
