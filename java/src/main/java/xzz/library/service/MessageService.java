package xzz.library.service;

import xzz.library.dto.MessageInfoDto;
import xzz.library.dto.MessageListDto;

public interface MessageService {
    MessageListDto getMessageList(String userId);

    MessageInfoDto getMessage(String userId, String messageId);

    String setStatus(String userId, String messageId, Integer status);

    String deleteMessage(String userId, String messageId);
}
