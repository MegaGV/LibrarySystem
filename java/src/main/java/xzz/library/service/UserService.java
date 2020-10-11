package xzz.library.service;

import xzz.library.dto.RecordsDto;
import xzz.library.dto.UserDto;
import xzz.library.pojo.User;

public interface UserService {
    String register(User user);
    String login(User user);
    UserDto getUserInfo(String id);

    String updatePassword(String id, String originPassword, String newPassword);


}
