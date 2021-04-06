package xzz.library.dao;

import xzz.library.pojo.Message;

import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(String id);

    int insert(Message record);

    Message selectByPrimaryKey(String id);

    List<Message> selectAll();

    int updateByPrimaryKey(Message record);

    List<Message> getMessagesByUserid(String userId);

    String getMessageStatus(Integer statusId);
}
