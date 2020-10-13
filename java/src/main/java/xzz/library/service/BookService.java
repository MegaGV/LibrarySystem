package xzz.library.service;

import xzz.library.dto.BooksDto;
import xzz.library.dto.BooksGetDto;
import xzz.library.pojo.Book;

public interface BookService {
    BooksDto getBookList(BooksGetDto booksGetDto);

    String borrowBook(String userId, String bookId);

    String returnBook(String borrowId);

    Book getBookDetail(String bookId);
}
