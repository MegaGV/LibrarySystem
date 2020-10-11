package xzz.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xzz.library.dto.BooksDto;
import xzz.library.pojo.Book;
import xzz.library.service.BookService;

@Controller
@RequestMapping("/book")
@CrossOrigin(allowCredentials = "true")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/getBookList")
    @ResponseBody
    public BooksDto getBookList(@RequestBody Book book){
        return bookService.getBookList(book);
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
}
