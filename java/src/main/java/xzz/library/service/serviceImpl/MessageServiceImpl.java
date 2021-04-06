package xzz.library.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xzz.library.dao.MessageMapper;
import xzz.library.dto.MessageInfoDto;
import xzz.library.dto.MessageListDto;
import xzz.library.pojo.Message;
import xzz.library.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public MessageListDto getMessages(String userId) {
        return new MessageListDto(messageMapper.getMessagesByUserid(userId));
    }

    @Override
    public MessageInfoDto getMessage(String userId, String messageId) {
        Message message = messageMapper.selectByPrimaryKey(messageId);
        MessageInfoDto messageInfoDto = new MessageInfoDto(message);
        messageInfoDto.setStatus(messageMapper.getMessageStatus(message.getStatus()));
        return messageInfoDto;
    }
}
