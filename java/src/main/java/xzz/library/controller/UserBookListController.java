package xzz.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xzz.library.dto.info.UserBookListInfoDto;
import xzz.library.dto.list.BookListDto;
import xzz.library.dto.list.UserBookListListDto;
import xzz.library.dto.search.UserBookListSearchDto;
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
    public UserBookListListDto getUserBookLists(String userId){
        return userBookListService.getUserBookLists(userId);
    }

    @GetMapping("/getUserBookList")
    @ResponseBody
    public UserBookListInfoDto getUserBookList(String userBookListId){
        return userBookListService.getUserBookList(userBookListId);
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
    public String addBook(String bookId, String userBookListId ){
        return userBookListService.updateBook(bookId, userBookListId, true);
    }

    @PostMapping("/removeBook")
    @ResponseBody
    public String removeBook(String bookId, String userBookListId ){
        return userBookListService.updateBook(bookId, userBookListId, false);
    }

    @GetMapping("/getListBooks")
    @ResponseBody
    public BookListDto getListBooks(String userBookListId){
        return userBookListService.getListBooks(userBookListId);
    }

    @PostMapping("/getAllUserBookLists")
    @ResponseBody
    public UserBookListListDto getAllUserBookLists(@RequestBody UserBookListSearchDto userBookListSearchDto){
        return userBookListService.getAllUserBookLists(userBookListSearchDto);
    }
}
