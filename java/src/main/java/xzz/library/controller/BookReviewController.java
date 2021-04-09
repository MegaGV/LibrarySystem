package xzz.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xzz.library.dto.info.BookReviewInfoDto;
import xzz.library.dto.list.BookReviewListDto;
import xzz.library.pojo.BookReview;
import xzz.library.service.BookReviewService;

@Controller
@RequestMapping("/bookReview")
@CrossOrigin(allowCredentials = "true")
public class BookReviewController {
    //========================================================================================
    // BookReviews
    // Operations: 获取书评列表(按书或按用户)、获取书评、撰写书评、删除书评、编辑书评
    //========================================================================================
    @Autowired
    private BookReviewService bookReviewService;

    @GetMapping("/getBookReviewsByBook")
    @ResponseBody
    public BookReviewListDto getBookReviewsByBook(String bookId){
        return bookReviewService.getBookReviewsByBook(bookId);
    }

    @GetMapping("/getBookReviewsByUser")
    @ResponseBody
    public BookReviewListDto getBookReviewsByUser(String userId){
        return bookReviewService.getBookReviewsByUser(userId);
    }

    @GetMapping("/getBookReview")
    @ResponseBody
    public BookReviewInfoDto getBookReview(String bookReviewId){
        return bookReviewService.getBookReview(bookReviewId);
    }

    @PostMapping("/publishBookReview")
    @ResponseBody
    public String publishBookReview(@RequestBody BookReview bookReview){
        return bookReviewService.publishBookReview(bookReview);
    }

    @PostMapping("deleteBookReview")
    @ResponseBody
    public String deleteBookReview(String userId, String bookReviewId){
        return bookReviewService.deleteBookReview(userId, bookReviewId);
    }

    @PostMapping("/updateBookReview")
    @ResponseBody
    public String updateBookReview(@RequestBody BookReview bookReview){
        return bookReviewService.updateBookReview(bookReview);
    }
}
