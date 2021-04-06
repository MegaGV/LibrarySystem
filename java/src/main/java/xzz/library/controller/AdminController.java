package xzz.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xzz.library.dto.*;
import xzz.library.pojo.*;
import xzz.library.service.AdminService;

@Controller
@RequestMapping("/admin")
@CrossOrigin(allowCredentials = "true")
public class AdminController {
    @Autowired
    private AdminService adminService;

    //========================================================================================
    // Users
    // Operations: 获取用户列表、获取用户信息、添加用户、删除用户、更新用户
    //========================================================================================
    @GetMapping("/getUsers")
    @ResponseBody
    public UsersListDto getUsers(Integer limit, Integer page){
        return adminService.getUsers(limit, page);
    }

    @GetMapping("/getUser")
    @ResponseBody
    public User getUser(String userId){
        return adminService.getUser(userId);
    }

    @PostMapping("/addUser")
    @ResponseBody
    public String addUser(@RequestBody User user){
        return adminService.addUser(user);
    }

    @PostMapping("/deleteUser")
    @ResponseBody
    public String deleteUser(@RequestBody String[] ids){
        return adminService.deleteUser(ids);
    }

    @PostMapping("/updateUser")
    @ResponseBody
    public String updateUser(@RequestBody User user){
        return adminService.updateUser(user);
    }

    //========================================================================================
    // Books
    // Operations: 获取图书列表、获取图书信息、添加图书、删除图书、更新图书
    //========================================================================================
    @PostMapping("/getBooks")
    @ResponseBody
    public BooksListDto getBooks(@RequestBody BooksSearchInfoDto booksSearchInfoDto){
        return adminService.getBooks(booksSearchInfoDto);
    }

    @GetMapping("/getBook")
    @ResponseBody
    public Book getBook(String bookId){
        return adminService.getBook(bookId);
    }

    @PostMapping("/addBook")
    @ResponseBody
    public String addBook(@RequestBody Book book){
        return adminService.addBook(book);
    }

    @PostMapping("/deleteBook")
    @ResponseBody
    public String deleteBook(@RequestBody String[] ids){
        return adminService.deleteBook(ids);
    }

    @PostMapping("/updateBook")
    @ResponseBody
    public String updateBook(@RequestBody Book book){
        return adminService.updateBook(book);
    }

    //========================================================================================
    // Records
    // Operations: 获取记录列表、获取记录、删除记录、更新记录(除列表外均按种类单独一个函数)
    //========================================================================================
    @GetMapping("/getRecords")
    @ResponseBody
    public RecordsListDto getRecords(Integer limit, Integer page, String recordType){
        return adminService.getRecords(limit, page, recordType);
    }

    @GetMapping("/getBR")
    @ResponseBody
    public BorrowRecord getBR(String borrowId){
        return adminService.getBR(borrowId);
    }

    @PostMapping("/deleteBR")
    @ResponseBody
    public String deleteBR(@RequestBody String[] ids){
        return adminService.deleteBR(ids);
    }

    @PostMapping("/updateBR")
    @ResponseBody
    public String updateBR(@RequestBody BorrowRecord borrowRecord){
        return adminService.updateBR(borrowRecord);
    }

    @GetMapping("/getRR")
    @ResponseBody
    public ReturnRecord getRR(String returnId){
        return adminService.getRR(returnId);
    }

    @PostMapping("/deleteRR")
    @ResponseBody
    public String deleteRR(@RequestBody String[] ids){
        return adminService.deleteRR(ids);
    }

    @PostMapping("/updateRR")
    @ResponseBody
    public String updateBR(@RequestBody ReturnRecord returnRecord){
        return adminService.updateRR(returnRecord);
    }

    @GetMapping("/getFR")
    @ResponseBody
    public FineRecord getFR(String fineId){
        return adminService.getFR(fineId);
    }

    @PostMapping("/deleteFR")
    @ResponseBody
    public String deleteFR(@RequestBody String[] ids){
        return adminService.deleteFR(ids);
    }

    @PostMapping("/updateFR")
    @ResponseBody
    public String updateFR(@RequestBody FineRecord fineRecord){
        return adminService.updateFR(fineRecord);
    }

    //========================================================================================
    // UserBookList
    // Operations: 获取书单列表、获取书单信息、添加书单、删除书单、更新书单
    //========================================================================================
    @PostMapping("/getUserBookLists")
    @ResponseBody
    public UserBookListListDto getUserBookLists(@RequestBody UserBookListSearchInfoDto userBookListSearchInfoDto){
        return adminService.getUserBookLists(userBookListSearchInfoDto);
    }

    @GetMapping("/getUserBookList")
    @ResponseBody
    public UserBookList getUserBookList(String userBookListId){
        return adminService.getUserBookList(userBookListId);
    }

    @PostMapping("/addUserBookList")
    @ResponseBody
    public String addUserBookList(@RequestBody UserBookList userBookList){
        return adminService.addUserBookList(userBookList);
    }

    @PostMapping("/deleteUserBookList")
    @ResponseBody
    public String deleteUserBookList(@RequestBody String[] ids){
        return adminService.deleteUserBookList(ids);
    }

    @PostMapping("/updateUserBookList")
    @ResponseBody
    public String updateUserBookList(@RequestBody UserBookList userBookList){
        return adminService.updateUserBookList(userBookList);
    }
}
