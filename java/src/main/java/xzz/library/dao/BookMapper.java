package xzz.library.dao;

import java.util.List;
import xzz.library.pojo.Book;

public interface BookMapper {
    int deleteByPrimaryKey(String id);

    int insert(Book record);

    Book selectByPrimaryKey(String id);

    List<Book> selectAll();

    int updateByPrimaryKey(Book record);
}