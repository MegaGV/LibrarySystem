package xzz.library.service;

import xzz.library.dto.BookTypeListDto;
import xzz.library.dto.BooksListDto;
import xzz.library.dto.BooksSearchInfoDto;
import xzz.library.pojo.Book;

public interface BookService {
    BooksListDto getBookList(BooksSearchInfoDto booksSearchInfoDto);

    String borrowBook(String userId, String bookId);

    String returnBook(String borrowId);

    Book getBookDetail(String bookId);

    BookTypeListDto getBookTypes();
}
