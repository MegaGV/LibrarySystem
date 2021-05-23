package xzz.library.service;

import xzz.library.dto.list.*;
import xzz.library.dto.search.*;
import xzz.library.pojo.*;

public interface AdminService {

    //========================================================================================
    // User
    //========================================================================================
    UserListDto getUsers(UserSearchDto userSearchDto);

    User getUser(String userId);

    String addUser(User user);

    String deleteUser(String[] ids);

    String updateUser(User user);

    //========================================================================================
    // Book
    //========================================================================================
    BookListDto getBooks(BookSearchDto bookSearchDto);

    Book getBook(String bookId);

    String addBook(Book book);

    String deleteBook(String[] ids);

    String updateBook(Book book);

    //========================================================================================
    // Record
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
    // UserBookList
    //========================================================================================
    UserBookListListDto getUserBookLists(UserBookListSearchDto userBookListSearchDto);

    UserBookList getUserBookList(String userBookListId);

    String addUserBookList(UserBookList userBookList);

    String deleteUserBookList(String[] ids);

    String updateUserBookList(UserBookList userBookList);

    //========================================================================================
    // Message
    //========================================================================================
    MessageListDto getMessages(MessageSearchDto messageSearchDto);

    Message getMessage(String messageId);

    String addMessage(Message message);

    String deleteMessage(String[] ids);

    String updateMessage(Message message);

    //========================================================================================
    // BookReview
    //========================================================================================
    BookReviewListDto getBookReviews(BookReviewSearchDto bookReviewSearchDto);

    BookReview getBookReview(String bookReviewId);

    String addBookReview(BookReview bookReview);

    String deleteBookReview(String[] ids);

    String updateBookReview(BookReview bookReview);

    //========================================================================================
    // Discuss
    //========================================================================================
    DiscussListDto getDiscusses(DiscussSearchDto discussSearchDto);

    Discuss getDiscuss(String discussId);

    String addDiscuss(Discuss discuss);

    String deleteDiscuss(String[] ids);

    String updateDiscuss(Discuss discuss);

    CommentListDto getComments(CommentSearchDto commentSearchDto);

    Comment getComment(String commentId);

    String addComment(Comment comment);

    String deleteComment(String[] ids);

    String updateComment(Comment comment);
}
