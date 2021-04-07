package xzz.library.dao;

import org.apache.ibatis.annotations.Param;
import xzz.library.dto.MessageSearchInfoDto;
import xzz.library.pojo.Message;

import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(String id);

    int insert(Message record);

    Message selectByPrimaryKey(String id);

    List<Message> selectAll();

    int updateByPrimaryKey(Message record);

    List<Message> getMessageListByUserid(String userId);

    String getMessageStatus(Integer statusId);

    void updateStatus(@Param("id") String id, @Param("status") Integer status);

    List<Message> getMessageList(MessageSearchInfoDto messageSearchInfoDto);

    int countMessage(MessageSearchInfoDto messageSearchInfoDto);

}
