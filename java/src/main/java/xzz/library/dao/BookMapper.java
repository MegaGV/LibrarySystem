package xzz.library.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import xzz.library.dto.BooksGetDto;
import xzz.library.pojo.Book;

public interface BookMapper {
    int deleteByPrimaryKey(String id);

    int insert(Book record);

    Book selectByPrimaryKey(String id);

    List<Book> selectAll();

    int updateByPrimaryKey(Book record);

    List<Book> getBookList(BooksGetDto booksGetDto);

    int countBook(BooksGetDto booksGetDto);
}