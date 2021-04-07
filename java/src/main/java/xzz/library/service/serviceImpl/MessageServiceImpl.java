package xzz.library.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public MessageListDto getMessageList(String userId) {
        return new MessageListDto(messageMapper.getMessageListByUserid(userId));
    }

    @Override
    public MessageInfoDto getMessage(String userId, String messageId) {
        Message message = messageMapper.selectByPrimaryKey(messageId);
        MessageInfoDto messageInfoDto = new MessageInfoDto(message);
        messageInfoDto.setStatus(messageMapper.getMessageStatus(message.getStatus()));
        return messageInfoDto;
    }

    @Override
    @Transactional
    public String setStatus(String userId, String messageId, Integer status) {
        if (userId == null || messageId == null || status == null)
            return "信息有误";
        if (!messageMapper.selectByPrimaryKey(messageId).getUserId().equals(userId))
            return "权限不足";

        try {
            messageMapper.updateStatus(messageId, status);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "通知更新失败";
        }
    }

    @Override
    @Transactional
    public String deleteMessage(String userId, String messageId) {
        if (userId == null || messageId == null)
            return "信息有误";
        if (!messageMapper.selectByPrimaryKey(messageId).getUserId().equals(userId))
            return "权限不足";

        try {
            messageMapper.deleteByPrimaryKey(messageId);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "删除通知失败";
        }
    }
}
