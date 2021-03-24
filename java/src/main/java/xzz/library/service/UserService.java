package xzz.library.service;

import xzz.library.dto.CreditInfoDto;
import xzz.library.dto.PasswordResetInfoDto;
import xzz.library.dto.UserInfoDto;
import xzz.library.pojo.User;

public interface UserService {
    String register(User user);

    User login(User user);

    UserInfoDto getUserInfo(String id);

    String updatePassword(PasswordResetInfoDto passwordResetInfoDto);

    String credit(CreditInfoDto creditInfoDto);

}
