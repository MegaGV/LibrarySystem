package xzz.library.service;

import xzz.library.dto.BooksDto;
import xzz.library.pojo.Book;

public interface BookService {
    BooksDto getBookList(Book book);

    String borrowBook(String userId, String bookId);

    String returnBook(String borrowId);
}
