package xzz.library.service;

import xzz.library.dto.*;
import xzz.library.pojo.*;

public interface AdminService {

    //========================================================================================
    // Users
    //========================================================================================
    UsersListDto getUsers(Integer limit, Integer page);

    User getUser(String userId);

    String addUser(User user);

    String deleteUser(String[] ids);

    String updateUser(User user);

    //========================================================================================
    // Books
    //========================================================================================
    BooksListDto getBooks(BooksSearchInfoDto booksSearchInfoDto);

    Book getBook(String bookId);

    String addBook(Book book);

    String deleteBook(String[] ids);

    String updateBook(Book book);

    //========================================================================================
    // Records
    //========================================================================================
    RecordsListDto getRecords(Integer limit, Integer page, String recordType);

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
    UserBookListListDto getUserBookListList(UserBookListSearchInfoDto userBookListSearchInfoDto);

    UserBookList getUserBookList(String userBookListId);

    String addUserBookList(UserBookList userBookList);

    String deleteUserBookList(String[] ids);

    String updateUserBookList(UserBookList userBookList);

    //========================================================================================
    // Messages
    //========================================================================================
    MessageListDto getMessageList(MessageSearchInfoDto messageSearchInfoDto);

    Message getMessage(String messageId);

    String addMessage(Message message);

    String deleteMessage(String[] ids);

    String updateMessage(Message message);
}
