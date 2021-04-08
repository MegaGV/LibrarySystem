package xzz.library.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xzz.library.dto.search.UserSearchDto;
import xzz.library.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    User selectByPrimaryKey(String id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User getUserByUsername(String username);

    String getUserStatus(Integer statusId);

    String getUserRole(Integer roleId);

    void updatePassword(@Param("id") String id, @Param("newPassword") String newPassword);

    List<User> getUsers(UserSearchDto userSearchDto);

    int countUsers(UserSearchDto userSearchDto);

    Integer getRoleId(String roleStr);

    Integer getStatusId(String statusStr);
}