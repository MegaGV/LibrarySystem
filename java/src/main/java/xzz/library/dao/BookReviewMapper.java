package xzz.library.dao;

import xzz.library.dto.search.BookReviewSearchDto;
import xzz.library.pojo.BookReview;

import java.util.List;

public interface BookReviewMapper {
    int deleteByPrimaryKey(String id);

    int insert(BookReview record);

    BookReview selectByPrimaryKey(String id);

    List<BookReview> selectAll();

    int updateByPrimaryKey(BookReview record);

    List<BookReview> getBookReviewsByBook(String bookId);

    List<BookReview> getBookReviewsByUser(String userId);

    String getReviewStatus(Integer statusId);

    Integer getStatusId(String statusStr);

    List<BookReview> getBookReviews(BookReviewSearchDto bookReviewSearchDto);

    int countBookReview(BookReviewSearchDto bookReviewSearchDto);
}