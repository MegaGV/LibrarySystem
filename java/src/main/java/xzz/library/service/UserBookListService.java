package xzz.library.service;

import xzz.library.dto.info.UserBookListInfoDto;
import xzz.library.dto.list.UserBookListListDto;
import xzz.library.pojo.UserBookList;

public interface UserBookListService {
    UserBookListListDto getUserBookLists(String userId);

    UserBookListInfoDto getUserBookList(String bookListId);

    String addUserBookList(UserBookList userBookList);

    String deleteUserBookList(String userId, String userBookListId);

    String updateUserBookList(UserBookList userBookList);

    String updateBook(String userId, String bookId, String userBookListId, Boolean isAdd);

}
