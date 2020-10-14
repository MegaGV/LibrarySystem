package xzz.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xzz.library.dto.*;
import xzz.library.pojo.*;
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
    public String deleteUser(String ids){
        return adminService.deleteUser(ids);
    }

    //========================================================================================
    //Books
    //========================================================================================
    @GetMapping("/getBooks")
    @ResponseBody
    public BooksDto getBooks(@RequestBody BooksGetDto booksGetDto){
        return adminService.getBooks(booksGetDto);
    }

    @PostMapping("/addBook")
    @ResponseBody
    public String addBook(@RequestBody Book book){
        return adminService.addBook(book);
    }

    @GetMapping("/getBook")
    @ResponseBody
    public Book getBook(String id){
        return adminService.getBook(id);
    }

    @PostMapping("/deleteBook")
    @ResponseBody
    public String deleteBook(String ids){
        return adminService.deleteBook(ids);
    }

    @PostMapping("/updateBook")
    @ResponseBody
    public String updateBook(@RequestBody Book book){
        return adminService.updateBook(book);
    }

    //========================================================================================
    //Records
    //========================================================================================
    @GetMapping("/getRecords")
    @ResponseBody
    public RecordsDto getRecords(@RequestBody RecordsGetDto recordsGetDto){
        return adminService.getRecords(recordsGetDto);
    }

    @GetMapping("/getBR")
    @ResponseBody
    public BorrowRecord getBR(String id){
        return adminService.getBR(id);
    }

    @PostMapping("/deleteBR")
    @ResponseBody
    public String deleteBR(String ids){
        return adminService.deleteBR(ids);
    }

    @PostMapping("/updateBR")
    @ResponseBody
    public String updateBR(@RequestBody BorrowRecord borrowRecord){
        return adminService.updateBR(borrowRecord);
    }

    @GetMapping("/getRR")
    @ResponseBody
    public ReturnRecord getRR(String id){
        return adminService.getRR(id);
    }

    @PostMapping("/deleteRR")
    @ResponseBody
    public String deleteRR(String ids){
        return adminService.deleteRR(ids);
    }

    @PostMapping("/updateRR")
    @ResponseBody
    public String updateBR(@RequestBody ReturnRecord returnRecord){
        return adminService.updateRR(returnRecord);
    }

    @GetMapping("/getFR")
    @ResponseBody
    public FineRecord getFR(String id){
        return adminService.getFR(id);
    }

    @PostMapping("/deleteFR")
    @ResponseBody
    public String deleteFR(String ids){
        return adminService.deleteFR(ids);
    }

    @PostMapping("/updateFR")
    @ResponseBody
    public String updateFR(@RequestBody FineRecord fineRecord){
        return adminService.updateFR(fineRecord);
    }
}
