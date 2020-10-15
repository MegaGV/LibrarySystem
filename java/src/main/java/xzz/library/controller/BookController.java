package xzz.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xzz.library.dto.BooksDto;
import xzz.library.dto.BooksGetDto;
import xzz.library.pojo.Book;
import xzz.library.pojo.Classification;
import xzz.library.service.BookService;

import java.util.List;

@Controller
@RequestMapping("/book")
@CrossOrigin(allowCredentials = "true")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/getBookList")
    @ResponseBody
    public BooksDto getBookList(@RequestBody BooksGetDto booksGetDto){
        return bookService.getBookList(booksGetDto);
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

    @GetMapping("/getFirstClassification")
    @ResponseBody
    public List<Classification> getFirstClassification(){
        return bookService.getFirstClassification();
    }

    @GetMapping("/getSecondClassification")
    @ResponseBody
    public List<Classification> getSecondClassification(String classifyChar){
        return bookService.getSecondClassification(classifyChar);
    }
}
