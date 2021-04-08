package xzz.library.service;

import xzz.library.dto.list.BookTypeListDto;
import xzz.library.dto.list.BookListDto;
import xzz.library.dto.search.BookSearchDto;
import xzz.library.pojo.Book;

public interface BookService {
    BookListDto getBooks(BookSearchDto bookSearchDto);

    String borrowBook(String userId, String bookId);

    String returnBook(String borrowId);

    Book getBookDetail(String bookId);

    BookTypeListDto getBookTypes();
}
