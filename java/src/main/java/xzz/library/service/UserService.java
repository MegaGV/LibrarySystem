package xzz.library.service;

import xzz.library.dto.info.CreditInfoDto;
import xzz.library.dto.info.PasswordResetInfoDto;
import xzz.library.dto.info.UserInfoDto;
import xzz.library.pojo.User;

public interface UserService {
    String register(User user);

    User login(User user);

    String userCheck(String userId);

    UserInfoDto getUserInfo(String userId);

    String updatePassword(PasswordResetInfoDto passwordResetInfoDto);

    String credit(CreditInfoDto creditInfoDto);

}
