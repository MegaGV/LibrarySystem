package xzz.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xzz.library.dto.UserBookListDto;
import xzz.library.dto.UserBookListsDto;
import xzz.library.pojo.UserBookList;
import xzz.library.service.UserBookListService;

@Controller
@RequestMapping("/bookList")
@CrossOrigin(allowCredentials = "true")
public class UserBookListController {
    //========================================================================================
    // BookList
    // Operations: 获取书单、获取书单书目、书单增删改
    //========================================================================================
    @Autowired
    private UserBookListService userBookListService;

    @GetMapping("/getUserBookLists")
    @ResponseBody
    public UserBookListsDto getUserBookLists(String userId){
        return userBookListService.getUserBookLists(userId);
    }

    @GetMapping("/getListBooks")
    @ResponseBody
    public UserBookListDto getListBooks(String bookListId){
        return userBookListService.getListBooks(bookListId);
    }

    @PostMapping("/addUserBookList")
    @ResponseBody
    public String addUserBookList(@RequestBody UserBookList userBookList){
        return userBookListService.addUserBookList(userBookList);
    }

    @PostMapping("/deleteUserBookList")
    @ResponseBody
    public String deleteUserBookList(String userBookListId, String userId){
        return userBookListService.deleteUserBookList(userBookListId, userId);
    }

    @PostMapping("/updateUserBookList")
    @ResponseBody
    public String updateUserBookList(@RequestBody UserBookList userBookList){
        return userBookListService.updateUserBookList(userBookList);
    }
}
