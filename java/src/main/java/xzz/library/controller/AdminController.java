package xzz.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xzz.library.dto.BooksDto;
import xzz.library.dto.UsersDto;
import xzz.library.pojo.Book;
import xzz.library.pojo.User;
import xzz.library.service.AdminService;

import java.util.List;

@Controller
@RequestMapping("/admin")
@CrossOrigin(allowCredentials = "true")
public class AdminController {
    @Autowired
    private AdminService adminService;

    //========================================================================================
    //Users
    //========================================================================================
    @GetMapping("/getUsers")
    @ResponseBody
    public UsersDto getUsers(Integer limit, Integer page){
        return adminService.getUsers(limit, page);
    }

    @GetMapping("/getUser")
    @ResponseBody
    public User getUser(String id){
        return adminService.getUser(id);
    }

    @PostMapping("/addUser")
    @ResponseBody
    public String addUser(@RequestBody User user){
        return adminService.addUser(user);
    }

    @PostMapping("/updateUser")
    @ResponseBody
    public String updateUser(@RequestBody User user){
        return adminService.updateUser(user);
    }

    @PostMapping("/deleteUser")
    @ResponseBody
    public String deleteUser(String[] ids){
        return adminService.deleteUser(ids);
    }

}
