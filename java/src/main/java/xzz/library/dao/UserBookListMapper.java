package xzz.library.dao;

import xzz.library.pojo.UserBookList;

import java.util.List;

public interface UserBookListMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserBookList record);

    UserBookList selectByPrimaryKey(String id);

    List<UserBookList> selectAll();

    int updateByPrimaryKey(UserBookList record);

    List<UserBookList> getBookListsByUserid(String userId);
}
