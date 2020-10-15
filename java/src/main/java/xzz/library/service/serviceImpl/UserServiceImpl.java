package xzz.library.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xzz.library.dao.UserMapper;
import xzz.library.dto.UserDto;
import xzz.library.pojo.User;
import xzz.library.service.UserService;
import xzz.library.util.MD5Utils;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public String register(User user) {
        if (userMapper.getUserByUsername(user.getUsername().trim()) != null)
            return "用户名重复";
        user.initial();
        while (userMapper.selectByPrimaryKey(user.getId()) != null)
            user.setId(UUID.randomUUID().toString());

        try{
            userMapper.insert(user);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "注册失败";
        }
    }

    @Override
    public User login(User user) {
        User dbUser = userMapper.getUserByUsername(user.getUsername().trim());
        if (dbUser == null || !dbUser.getPassword().equals(MD5Utils.md5Code(user.getUsername().trim(), user.getPassword())))
            return null;
        User u = new User();
        u.setId(dbUser.getId());
        u.setRole(dbUser.getRole());
        return u;
    }

    @Override
    public UserDto getUserInfo(String id) {
        User dbUser = userMapper.selectByPrimaryKey(id);
        if (dbUser == null)
            return null;
        UserDto userDto = new UserDto(dbUser);
        userDto.setStatus(userMapper.getUserStatus(dbUser.getStatus()));
        userDto.setRole(userMapper.getUserRole(dbUser.getRole()));
        return userDto;
    }

    @Override
    @Transactional
    public String updatePassword(String id, String originPassword, String newPassword) {
        User dbUser = userMapper.selectByPrimaryKey(id);
        if (dbUser == null)
            return "用户错误";
        if (!MD5Utils.md5Code(dbUser.getUsername(),originPassword).equals(dbUser.getPassword()))
            return "原密码有误，密码修改失败";

        try {
            userMapper.updatePassword(id, MD5Utils.md5Code(dbUser.getUsername(),newPassword));
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "密码修改失败";
        }
    }

    @Override
    @Transactional
    public String credit(String id, Double money) {
        User dbUser = userMapper.selectByPrimaryKey(id);
        if (dbUser == null)
            return "用户错误";
        if (money == null || money < 0)
            return "金额错误";
        dbUser.credit(money);

        try{
            userMapper.updateByPrimaryKey(dbUser);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "充值失败";
        }
    }

}
