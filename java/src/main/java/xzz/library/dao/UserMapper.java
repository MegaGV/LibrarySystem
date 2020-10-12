package xzz.library.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import xzz.library.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    User selectByPrimaryKey(String id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User getUserByUsername(String username);

    String getUserStatus(Integer id);

    String getUserRole(Integer id);

    void updatePassword(@Param("id") String id, @Param("newPassword") String newPassword);

    List<User> getUsers(@Param("limit") Integer limit, @Param("start") Integer start);

    Integer countUsers();
}