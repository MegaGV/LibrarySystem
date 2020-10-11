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
        User dbUser = userMapper.getUserByUsername(user.getUsername());
        if (dbUser != null){
            return "用户名重复";
        }

        String id = UUID.randomUUID().toString();
        while (userMapper.selectByPrimaryKey(id) != null){
            id = UUID.randomUUID().toString();
        }
        user.setId(id);
        user.setRole(0);
        user.setBorrowed(0);
        user.setStatus(0);
        user.setBalance(0.00);
        user.setPassword(MD5Utils.md5Code(user.getUsername(), user.getPassword()));
        try{
            userMapper.insert(user);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "注册失败";
        }
    }

    @Override
    public String login(User user) {
        User dbUser = userMapper.getUserByUsername(user.getUsername().trim());
        if (dbUser == null){
            return null;
        }
        else{
            if (!MD5Utils.md5Code(user.getUsername(), user.getPassword()).equals(dbUser.getPassword())){
                return null;
            }
            else{
                return dbUser.getId();
            }
        }
    }

    @Override
    public UserDto getUserInfo(String id) {
        User dbUser = userMapper.selectByPrimaryKey(id);
        UserDto userDto = new UserDto(dbUser);
        userDto.setStatus(userMapper.getUserStatus(dbUser.getStatus()));
        userDto.setRole(userMapper.getUserRole(dbUser.getRole()));
        return userDto;
    }

    @Override
    @Transactional
    public String updatePassword(String id, String originPassword, String newPassword) {
        User dbUser = userMapper.selectByPrimaryKey(id);

        if (MD5Utils.md5Code(dbUser.getUsername(),originPassword).equals(dbUser.getPassword())){
            try {
                userMapper.updatePassword(id, MD5Utils.md5Code(dbUser.getUsername(),newPassword));
                return null;
            } catch (Exception e){
                e.printStackTrace();
                return "密码修改失败";
            }
        }
        else{
            return "原密码有误，密码修改失败";
        }
    }

}
