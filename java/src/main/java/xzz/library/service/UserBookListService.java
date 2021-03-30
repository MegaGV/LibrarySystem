package xzz.library.service;

import xzz.library.dto.UserBookListDto;
import xzz.library.dto.UserBookListsDto;
import xzz.library.pojo.UserBookList;

public interface UserBookListService {
    UserBookListsDto getUserBookLists(String userId);

    UserBookListDto getListBooks(String bookListId);

    String addUserBookList(UserBookList userBookList);

    String deleteUserBookList(String userBookListId, String userId);

    String updateUserBookList(UserBookList userBookList);
}
