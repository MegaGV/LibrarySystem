package xzz.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xzz.library.dto.info.MessageInfoDto;
import xzz.library.dto.list.MessageListDto;
import xzz.library.service.MessageService;

@Controller
@RequestMapping("/message")
@CrossOrigin(allowCredentials = "true")
public class MessageController {
    //========================================================================================
    // Message
    // Operations: 获取通知列表、获取通知、设置状态、删除通知
    //========================================================================================
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

    @PostMapping("/setRead")
    @ResponseBody
    public String setRead(String[] ids){
        return messageService.setStatus(ids, 1);
    }

    @PostMapping("/setUnRead")
    @ResponseBody
    public String setUnRead(String[] ids){
        return messageService.setStatus(ids, 0);
    }

    @PostMapping("/deleteMessage")
    @ResponseBody
    public String deleteMessage(String[] ids){
        return messageService.deleteMessage(ids);
    }
}
