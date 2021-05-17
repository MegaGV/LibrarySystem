package xzz.library.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xzz.library.dao.MessageMapper;
import xzz.library.dto.info.MessageInfoDto;
import xzz.library.dto.list.MessageListDto;
import xzz.library.pojo.Message;
import xzz.library.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public MessageListDto getMessages(String userId) {
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
    public String setStatus(String[] ids, Integer status) {
        if (ids == null)
            return "消息错误";
        if (ids.length == 0)
            return "无选中消息记录";

        try {
            for (String id : ids)
                messageMapper.updateStatus(id, status);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "通知更新失败";
        }
    }

    @Override
    @Transactional
    public String deleteMessage(String[] ids) {
        if (ids == null)
            return "消息错误";
        if (ids.length == 0)
            return "无选中消息记录";

        try {
            for (String id : ids)
                messageMapper.deleteByPrimaryKey(id);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "删除通知失败";
        }
    }
}
