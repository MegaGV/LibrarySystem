package xzz.library.dao;

import java.util.List;
import xzz.library.dto.search.BookSearchDto;
import xzz.library.pojo.Book;

public interface BookMapper {
    int deleteByPrimaryKey(String id);

    int insert(Book record);

    Book selectByPrimaryKey(String id);

    List<Book> selectAll();

    int updateByPrimaryKey(Book record);

    List<Book> getBooks(BookSearchDto bookSearchDto);

    int countBook(BookSearchDto bookSearchDto);
}