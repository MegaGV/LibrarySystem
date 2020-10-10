package xzz.library.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xzz.library.dao.UserMapper;
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
            return "注册成功";
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
}
