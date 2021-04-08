package xzz.library.service;

import xzz.library.dto.info.MessageInfoDto;
import xzz.library.dto.list.MessageListDto;

public interface MessageService {
    MessageListDto getMessages(String userId);

    MessageInfoDto getMessage(String userId, String messageId);

    String setStatus(String userId, String messageId, Integer status);

    String deleteMessage(String userId, String messageId);
}
