package xzz.library.service;

import xzz.library.dto.*;
import xzz.library.pojo.*;

public interface AdminService {

    //========================================================================================
    //Users
    //========================================================================================
    UsersDto getUsers(Integer limit, Integer page);

    User getUser(String id);

    String updateUser(User user);

    String addUser(User user);

    String deleteUser(String[] ids);

    //========================================================================================
    //Books
    //========================================================================================
    BooksDto getBooks(BooksGetDto booksGetDto);

    String addBook(Book book);

    Book getBook(String id);

    String deleteBook(String ids);

    String updateBook(Book book);

    //========================================================================================
    //Records
    //========================================================================================
    RecordsDto getRecords(RecordsGetDto recordsGetDto);

    BorrowRecord getBR(String id);

    String deleteBR(String ids);

    String updateBR(BorrowRecord borrowRecord);

    ReturnRecord getRR(String id);

    String deleteRR(String ids);

    String updateRR(ReturnRecord returnRecord);

    FineRecord getFR(String id);

    String deleteFR(String ids);

    String updateFR(FineRecord fineRecord);
}
