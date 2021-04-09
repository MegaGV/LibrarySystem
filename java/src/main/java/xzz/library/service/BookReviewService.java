package xzz.library.service;

import xzz.library.dto.info.BookReviewInfoDto;
import xzz.library.dto.list.BookReviewListDto;
import xzz.library.pojo.BookReview;

public interface BookReviewService {

    BookReviewListDto getBookReviewsByBook(String bookId);

    BookReviewListDto getBookReviewsByUser(String userId);

    BookReviewInfoDto getBookReview(String bookReviewId);

    String publishBookReview(BookReview bookReview);

    String deleteBookReview(String userId, String bookReviewId);

    String updateBookReview(BookReview bookReview);
}
