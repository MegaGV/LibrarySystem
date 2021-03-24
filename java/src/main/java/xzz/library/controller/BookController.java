package xzz.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xzz.library.dto.BookTypeListDto;
import xzz.library.dto.BooksListDto;
import xzz.library.dto.BooksSearchInfoDto;
import xzz.library.pojo.Book;
import xzz.library.service.BookService;

@Controller
@RequestMapping("/book")
@CrossOrigin(allowCredentials = "true")
public class BookController {
    //========================================================================================
    // Operations: 获取图书列表、获取图书信息、借书、还书、获取图书种类
    //========================================================================================
    @Autowired
    private BookService bookService;

    @PostMapping("/getBookList")
    @ResponseBody
    public BooksListDto getBookList(@RequestBody BooksSearchInfoDto booksSearchInfoDto){
        return bookService.getBookList(booksSearchInfoDto);
    }

    @GetMapping("/getBookDetail")
    @ResponseBody
    public Book getBookDetail(String bookId){
        return bookService.getBookDetail(bookId);
    }

    @PostMapping("/borrowBook")
    @ResponseBody
    public String borrowBook(String userId, String bookId){
        return bookService.borrowBook(userId, bookId);
    }

    @PostMapping("/returnBook")
    @ResponseBody
    public String returnBook(String borrowId){
        return bookService.returnBook(borrowId);
    }

    @GetMapping("/getBookTypes")
    @ResponseBody
    public BookTypeListDto getBookTypes(){
        return bookService.getBookTypes();
    }

}
