package xzz.library.service;

import xzz.library.dto.CreditDto;
import xzz.library.dto.PasswordResetDto;
import xzz.library.dto.UserDto;
import xzz.library.pojo.User;

public interface UserService {
    String register(User user);
    User login(User user);
    UserDto getUserInfo(String id);
    String updatePassword(PasswordResetDto passwordResetDto);
    String credit(CreditDto creditDto);
}
