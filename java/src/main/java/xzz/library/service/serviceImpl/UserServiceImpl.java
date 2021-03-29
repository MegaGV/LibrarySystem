package xzz.library.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xzz.library.dao.BookMapper;
import xzz.library.dao.UserBookListMapper;
import xzz.library.dao.UserMapper;
import xzz.library.dto.*;
import xzz.library.pojo.User;
import xzz.library.pojo.UserBookList;
import xzz.library.service.UserService;
import xzz.library.util.MD5Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserBookListMapper userBookListMapper;

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
        if (dbUser == null || dbUser.getStatus() == 3 ||!dbUser.getPassword().equals(MD5Utils.md5Code(user.getUsername().trim(), user.getPassword())))
            return null;
        User u = new User();
        u.setId(dbUser.getId());
        u.setRole(dbUser.getRole());
        return u;
    }

    @Override
    public String userCheck(String userId) {
        User dbUser = userMapper.selectByPrimaryKey(userId);
        if (dbUser == null)
            return null;
        else
            return userMapper.getUserRole((dbUser.getRole()));
    }

    @Override
    public UserInfoDto getUserInfo(String userId) {
        User dbUser = userMapper.selectByPrimaryKey(userId);
        UserInfoDto userInfoDto = new UserInfoDto(dbUser);
        userInfoDto.setStatus(userMapper.getUserStatus(dbUser.getStatus()));
        userInfoDto.setRole(userMapper.getUserRole(dbUser.getRole()));
        return userInfoDto;
    }

    @Override
    @Transactional
    public String updatePassword(PasswordResetInfoDto passwordResetInfoDto) {
        User dbUser = userMapper.selectByPrimaryKey(passwordResetInfoDto.getUserId());
        if (dbUser == null)
            return "用户错误";
        if (!MD5Utils.md5Code(dbUser.getUsername(), passwordResetInfoDto.getOriginPassword()).equals(dbUser.getPassword()))
            return "原密码有误，密码修改失败";

        try {
            userMapper.updatePassword(passwordResetInfoDto.getUserId(),
                    MD5Utils.md5Code(dbUser.getUsername(), passwordResetInfoDto.getNewPassword()));
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "密码修改失败";
        }
    }

    @Override
    @Transactional
    public String credit(CreditInfoDto creditInfoDto) {
        User dbUser = userMapper.selectByPrimaryKey(creditInfoDto.getUserId());
        if (dbUser == null)
            return "用户错误";
        if (creditInfoDto.getMoney() == null || creditInfoDto.getMoney() < 0)
            return "金额错误";
        dbUser.credit(creditInfoDto.getMoney());

        try{
            userMapper.updateByPrimaryKey(dbUser);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "充值失败";
        }
    }

    @Override
    public UserBookListsDto getUserBookLists(String userId) {
        List<UserBookList> userBookLists = userBookListMapper.getBookListsByUserid(userId);
        return new UserBookListsDto(userBookLists, userBookLists.size());
    }

    public BooksListDto getListBooks(String userBookListId){
        UserBookList userBookList = userBookListMapper.selectByPrimaryKey(userBookListId);

        UserBookListDto userBookListDto = new UserBookListDto(userBookList);
        String[] books = userBookList.getBooks().split(",");
        for (String book : books){
            userBookListDto.addBook(bookMapper.selectByPrimaryKey(book));
        }
//        userBookListDtos.add(userBookListDto);

        return new BooksListDto()
    }

}
