package xzz.library.dao;

import org.apache.ibatis.annotations.Param;
import xzz.library.dto.UserBookListSearchInfoDto;
import xzz.library.pojo.UserBookList;

import java.util.List;

public interface UserBookListMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserBookList record);

    UserBookList selectByPrimaryKey(String id);

    List<UserBookList> selectAll();

    int updateByPrimaryKey(UserBookList record);

    List<UserBookList> getBookListsByUserid(String userId);

    void updateBooksByPrimaryKey(@Param("id") String id, @Param("books")  String books);

    List<UserBookList> getUserBookLists(UserBookListSearchInfoDto userBookListSearchInfoDto);

    int countUserBookList(UserBookListSearchInfoDto userBookListSearchInfoDto);
}
