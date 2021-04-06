package xzz.library.service;

import xzz.library.dto.MessageInfoDto;
import xzz.library.dto.MessageListDto;

public interface MessageService {
    MessageListDto getMessages(String userId);

    MessageInfoDto getMessage(String userId, String messageId);
}
