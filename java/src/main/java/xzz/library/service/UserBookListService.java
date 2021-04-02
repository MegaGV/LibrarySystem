package xzz.library.service;

import xzz.library.dto.UserBookListDto;
import xzz.library.dto.UserBookListsDto;
import xzz.library.pojo.UserBookList;

public interface UserBookListService {
    UserBookListsDto getUserBookLists(String userId);

    UserBookListDto getUserBookList(String bookListId);

    String addUserBookList(UserBookList userBookList);

    String deleteUserBookList(String userId, String userBookListId);

    String updateUserBookList(UserBookList userBookList);

    String updateBook(String userId, String bookId, String userBookListId, Boolean isAdd);

}
