package xzz.library.service;

import xzz.library.dto.UserDto;
import xzz.library.pojo.User;

public interface UserService {
    String register(User user);
    User login(User user);
    UserDto getUserInfo(String id);
    String updatePassword(String id, String originPassword, String newPassword);
    String credit(String id, Double money);
}
