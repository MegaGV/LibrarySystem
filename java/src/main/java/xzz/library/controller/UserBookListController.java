package xzz.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xzz.library.dto.UserBookListDto;
import xzz.library.dto.UserBookListsDto;
import xzz.library.pojo.UserBookList;
import xzz.library.service.UserBookListService;

@Controller
@RequestMapping("/userBookList")
@CrossOrigin(allowCredentials = "true")
public class UserBookListController {
    //========================================================================================
    // BookList
    // Operations: 获取书单、获取书单书目、书单增删改、添加或移出图书
    //========================================================================================
    @Autowired
    private UserBookListService userBookListService;

    @GetMapping("/getUserBookLists")
    @ResponseBody
    public UserBookListsDto getUserBookLists(String userId){
        return userBookListService.getUserBookLists(userId);
    }

    @GetMapping("/getUserBookList")
    @ResponseBody
    public UserBookListDto getUserBookList(String bookListId){
        return userBookListService.getUserBookList(bookListId);
    }

    @PostMapping("/addUserBookList")
    @ResponseBody
    public String addUserBookList(@RequestBody UserBookList userBookList){
        return userBookListService.addUserBookList(userBookList);
    }

    @PostMapping("/deleteUserBookList")
    @ResponseBody
    public String deleteUserBookList(String userId, String userBookListId){
        return userBookListService.deleteUserBookList(userId, userBookListId);
    }

    @PostMapping("/updateUserBookList")
    @ResponseBody
    public String updateUserBookList(@RequestBody UserBookList userBookList){
        return userBookListService.updateUserBookList(userBookList);
    }

    @PostMapping("/addBook")
    @ResponseBody
    public String addBook(String userId, String bookId, String userBookListId ){
        return userBookListService.updateBook(userId, bookId, userBookListId, true);
    }

    @PostMapping("/removeBook")
    @ResponseBody
    public String removeBook(String userId, String bookId, String userBookListId ){
        return userBookListService.updateBook(userId, bookId, userBookListId, false);
    }
}
