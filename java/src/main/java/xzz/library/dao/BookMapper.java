package xzz.library.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import xzz.library.pojo.Book;

public interface BookMapper {
    int deleteByPrimaryKey(String id);

    int insert(Book record);

    Book selectByPrimaryKey(String id);

    List<Book> selectAll();

    int updateByPrimaryKey(Book record);

    List<Book> getBookList(@Param("book_name") String bookName,
                           @Param("book_type") String bookType,
                           @Param("author") String author,
                           @Param("publisher") String publisher,
                           @Param("stock") Integer stock,
                           @Param("limit") Integer limit,
                           @Param("start") Integer start);

    int countBook(@Param("book_name") String bookName,
                   @Param("book_type") String bookType,
                   @Param("author") String author,
                   @Param("publisher") String publisher,
                   @Param("stock") Integer stock);
}