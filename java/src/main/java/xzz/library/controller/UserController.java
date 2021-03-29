package xzz.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xzz.library.dto.CreditInfoDto;
import xzz.library.dto.PasswordResetInfoDto;
import xzz.library.dto.UserBookListsDto;
import xzz.library.dto.UserInfoDto;
import xzz.library.pojo.User;
import xzz.library.service.UserService;


@Controller
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true")
public class UserController {
    //========================================================================================
    // Basic system
    // Operations: 注册、登录、检测用户存在
    //========================================================================================
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    @ResponseBody
    public User login(@RequestBody User user) {
        return userService.login(user);
    }

    @GetMapping("/userCheck")
    @ResponseBody
    public String userCheck(String userId) {
        return userService.userCheck(userId);
    }

    //========================================================================================
    // User
    // Operations: 获取用户信息、更新密码、充值
    //========================================================================================
    @GetMapping("/getUserInfo")
    @ResponseBody
    public UserInfoDto getUserInfo(String userId){
        return userService.getUserInfo(userId);
    }

    @PostMapping("/updatePassword")
    @ResponseBody
    public String updatePassword(@RequestBody PasswordResetInfoDto passwordResetInfoDto){
        return userService.updatePassword(passwordResetInfoDto);
    }

    @PostMapping("/credit")
    @ResponseBody
    public String credit(@RequestBody CreditInfoDto creditInfoDto){
        return userService.credit(creditInfoDto);
    }

    @GetMapping("/getUserBookLists")
    @ResponseBody
    public UserBookListsDto getUserBookLists(String userId){
        return userService.getUserBookLists(userId);
    }
}
