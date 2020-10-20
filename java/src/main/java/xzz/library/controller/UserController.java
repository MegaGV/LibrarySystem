package xzz.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xzz.library.dto.CreditDto;
import xzz.library.dto.PasswordResetDto;
import xzz.library.dto.UserDto;
import xzz.library.pojo.User;
import xzz.library.service.UserService;


@Controller
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true")
public class UserController {
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

    @GetMapping("/getUserInfo")
    @ResponseBody
    public UserDto getUserInfo(String id){
        return userService.getUserInfo(id);
    }

    @PostMapping("/updatePassword")
    @ResponseBody
    public String updatePassword(@RequestBody PasswordResetDto passwordResetDto){
        return userService.updatePassword(passwordResetDto);
    }

    @PostMapping("/credit")
    @ResponseBody
    public String credit(@RequestBody CreditDto creditDto){
        return userService.credit(creditDto);
    }
}
