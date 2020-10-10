package xzz.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xzz.library.pojo.User;
import xzz.library.service.UserService;


@Controller
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestBody User user) {
        return service.register(user);
    }

    @GetMapping("/login")
    @ResponseBody
    public String login(@RequestBody User user) {
        return service.login(user);
    }

}
