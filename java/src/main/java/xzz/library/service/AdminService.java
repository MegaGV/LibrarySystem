package xzz.library.service;

import xzz.library.dto.list.*;
import xzz.library.dto.search.*;
import xzz.library.pojo.*;

public interface AdminService {

    //========================================================================================
    // Users
    //========================================================================================
    UserListDto getUsers(UserSearchDto userSearchDto);

    User getUser(String userId);

    String addUser(User user);

    String deleteUser(String[] ids);

    String updateUser(User user);

    //========================================================================================
    // Books
    //========================================================================================
    BookListDto getBooks(BookSearchDto bookSearchDto);

    Book getBook(String bookId);

    String addBook(Book book);

    String deleteBook(String[] ids);

    String updateBook(Book book);

    //========================================================================================
    // Records
    //========================================================================================
    RecordListDto getRecords(RecordSearchDto recordSearchDto);

    BorrowRecord getBR(String borrowId);

    String deleteBR(String[] ids);

    String updateBR(BorrowRecord borrowRecord);

    ReturnRecord getRR(String returnId);

    String deleteRR(String[] ids);

    String updateRR(ReturnRecord returnRecord);

    FineRecord getFR(String fineId);

    String deleteFR(String[] ids);

    String updateFR(FineRecord fineRecord);

    //========================================================================================
    // UserBookLists
    //========================================================================================
    UserBookListListDto getUserBookLists(UserBookListSearchDto userBookListSearchDto);

    UserBookList getUserBookList(String userBookListId);

    String addUserBookList(UserBookList userBookList);

    String deleteUserBookList(String[] ids);

    String updateUserBookList(UserBookList userBookList);

    //========================================================================================
    // Messages
    //========================================================================================
    MessageListDto getMessages(MessageSearchDto messageSearchDto);

    Message getMessage(String messageId);

    String addMessage(Message message);

    String deleteMessage(String[] ids);

    String updateMessage(Message message);
}
