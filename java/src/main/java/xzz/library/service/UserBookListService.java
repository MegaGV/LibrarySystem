package xzz.library.service;

import xzz.library.dto.info.UserBookListInfoDto;
import xzz.library.dto.list.BookListDto;
import xzz.library.dto.list.UserBookListListDto;
import xzz.library.dto.search.UserBookListSearchDto;
import xzz.library.pojo.UserBookList;

public interface UserBookListService {
    UserBookListListDto getUserBookLists(String userId);

    UserBookListInfoDto getUserBookList(String bookListId);

    String addUserBookList(UserBookList userBookList);

    String deleteUserBookList(String userId, String userBookListId);

    String updateUserBookList(UserBookList userBookList);

    String updateBook(String bookId, String userBookListId, Boolean isAdd);

    BookListDto getListBooks(String userBookListId);

    UserBookListListDto getAllUserBookLists(UserBookListSearchDto userBookListSearchDto);
}
