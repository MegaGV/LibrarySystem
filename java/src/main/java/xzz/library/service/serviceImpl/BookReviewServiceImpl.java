package xzz.library.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xzz.library.dao.*;
import xzz.library.dto.info.BookReviewInfoDto;
import xzz.library.dto.list.BookReviewListDto;
import xzz.library.pojo.*;
import xzz.library.service.BookReviewService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookReviewServiceImpl implements BookReviewService {
    @Autowired
    private BookReviewMapper bookReviewMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public BookReviewListDto getBookReviewsByBook(String bookId) {
        return createBookReviewListDto(bookReviewMapper.getBookReviewsByBook(bookId));
    }

    @Override
    public BookReviewListDto getBookReviewsByUser(String userId) {
        return createBookReviewListDto(bookReviewMapper.getBookReviewsByUser(userId));
    }

    private BookReviewListDto createBookReviewListDto(List<BookReview> bookReviews){
        List<BookReviewInfoDto> bookReviewInfoDtos = new ArrayList<>();
        for (BookReview bookReview : bookReviews)
            bookReviewInfoDtos.add(createBookReviewInfoDto(bookReview));
        return new BookReviewListDto(bookReviewInfoDtos);
    }

    private BookReviewInfoDto createBookReviewInfoDto(BookReview bookReview){
        BookReviewInfoDto bookReviewInfoDto = new BookReviewInfoDto(bookReview);
        bookReviewInfoDto.setBook(bookMapper.selectByPrimaryKey(bookReview.getBookId()));
        bookReviewInfoDto.setUser(userMapper.selectByPrimaryKey(bookReview.getUserId()));
        bookReviewInfoDto.setStatus(bookReviewMapper.getReviewStatus(bookReview.getStatus()));
        return bookReviewInfoDto;
    }

    @Override
    public BookReviewInfoDto getBookReview(String bookReviewId) {
        return createBookReviewInfoDto(bookReviewMapper.selectByPrimaryKey(bookReviewId));
    }

    @Override
    @Transactional
    public String publishBookReview(BookReview bookReview) {
        if (bookReview == null)
            return "信息有误";
        bookReview.initial();
        while (bookReviewMapper.selectByPrimaryKey(bookReview.getId()) != null)
            bookReview.setId(UUID.randomUUID().toString());

        try{
            bookReviewMapper.insert(bookReview);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "发布书评失败";
        }
    }

    @Override
    @Transactional
    public String deleteBookReview(String userId, String bookReviewId) {
        if (userId == null || bookReviewId == null)
            return "信息有误";
        BookReview bookReview = bookReviewMapper.selectByPrimaryKey(bookReviewId);
        if (!userId.equals(bookReview.getUserId()))
            return "权限不足";

        try{
            bookReviewMapper.deleteByPrimaryKey(bookReviewId);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "删除书评失败";
        }
    }

    @Override
    @Transactional
    public String updateBookReview(BookReview bookReview) {
        if (bookReview == null)
            return "信息有误";
        if (!bookReview.getUserId().equals(bookReviewMapper.selectByPrimaryKey(bookReview.getUserId())))
            return "权限不足";
        bookReview.setStatus(0); // 编辑后需重新审核

        try{
            bookReviewMapper.updateByPrimaryKey(bookReview);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "更新书评失败";
        }
    }
}
