package xzz.library.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xzz.library.dao.*;
import xzz.library.dto.list.*;
import xzz.library.dto.search.*;
import xzz.library.pojo.*;
import xzz.library.service.AdminService;
import xzz.library.util.MD5Utils;

import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BorrowRecordMapper borrowRecordMapper;
    @Autowired
    private ReturnRecordMapper returnRecordMapper;
    @Autowired
    private FineRecordMapper fineRecordMapper;
    @Autowired
    private UserBookListMapper userBookListMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private BookReviewMapper bookReviewMapper;

    //========================================================================================
    // User
    //========================================================================================
    @Override
    public UserListDto getUsers(UserSearchDto userSearchDto) {
        userSearchDto.initial();
        return new UserListDto(userMapper.getUsers(userSearchDto), userMapper.countUsers(userSearchDto));
    }

    @Override
    public User getUser(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    @Transactional
    public String addUser(User user) {
        if (user == null)
            return "用户错误";
        if (userMapper.getUserByUsername(user.getUsername()) != null)
            return "用户名重复";
        user.initial();
        while (userMapper.selectByPrimaryKey(user.getId()) != null)
            user.setId(UUID.randomUUID().toString());
        
        try {
            userMapper.insert(user);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "新增用户失败";
        }
    }

    @Override
    @Transactional
    public String deleteUser(String[] ids) {
        if (ids == null)
            return "用户错误";
        if (ids.length == 0)
            return "无选中用户";
        
        try {
            for (String id : ids)
                userMapper.deleteByPrimaryKey(id);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "删除用户失败";
        }
    }

    @Override
    @Transactional
    public String updateUser(User user) {
        if (user == null)
            return "用户错误";
        User dbUser = userMapper.selectByPrimaryKey(user.getId());
        // 管理员看到的密码也是经过md5加密过的，所以直接两个密码进行比对
        if (!dbUser.getPassword().equals(user.getPassword()))
            user.setPassword(MD5Utils.md5Code(user.getUsername(), user.getPassword()));

        try {
            userMapper.updateByPrimaryKey(user);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "修改失败";
        }
    }

    //========================================================================================
    // Book
    //========================================================================================
    @Override
    public BookListDto getBooks(BookSearchDto bookSearchDto) {
        bookSearchDto.initial();
        return new BookListDto(bookMapper.getBooks(bookSearchDto),bookMapper.countBook(bookSearchDto));
    }

    @Override
    @Transactional
    public String addBook(Book book) {
        if (book == null)
            return "图书错误";
        book.initial();
        while (bookMapper.selectByPrimaryKey(book.getId()) != null)
            book.setId(UUID.randomUUID().toString());
        
        try {
            bookMapper.insert(book);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "图书添加失败";
        }
    }

    @Override
    public Book getBook(String bookId) {
        return bookMapper.selectByPrimaryKey(bookId);
    }

    @Override
    @Transactional
    public String deleteBook(String[] ids) {
        if (ids == null)
            return "图书错误";
        if (ids.length == 0)
            return "无选中图书";
        
        try {
            for (String id : ids)
                bookMapper.deleteByPrimaryKey(id);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "删除图书失败";
        }
    }

    @Override
    @Transactional
    public String updateBook(Book book) {
        if(book == null)
            return "图书错误";
        
        try{
            bookMapper.updateByPrimaryKey(book);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "修改图书失败";
        }
    }

    //========================================================================================
    // Record
    //========================================================================================
    @Override
    public RecordListDto getRecords(RecordSearchDto recordSearchDto){
        recordSearchDto.initial();
        switch (recordSearchDto.getRecordType()){
            case "borrow" :
                return new RecordListDto(borrowRecordMapper.getRecords(recordSearchDto),
                        borrowRecordMapper.countRecords(recordSearchDto));
            case "return":
                return new RecordListDto(returnRecordMapper.getRecords(recordSearchDto),
                        returnRecordMapper.countRecords(recordSearchDto));
            case "fine":
                return new RecordListDto(fineRecordMapper.getRecords(recordSearchDto),
                        fineRecordMapper.countRecords(recordSearchDto));
            default:
        }
        return null;
    }

    @Override
    public BorrowRecord getBR(String borrowId) {
        return borrowRecordMapper.selectByPrimaryKey(borrowId);
    }

    @Override
    @Transactional
    public String deleteBR(String[] ids) {
        if (ids == null)
            return "借阅记录错误";
        if (ids.length == 0)
            return "无选中借阅记录";
        
        try {
            for (String id : ids)
                borrowRecordMapper.deleteByPrimaryKey(id);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "借阅记录删除失败";
        }
    }

    @Override
    @Transactional
    public String updateBR(BorrowRecord borrowRecord) {
        if (borrowRecord == null)
            return "借阅信息错误";
        
        try {
            borrowRecordMapper.updateByPrimaryKey(borrowRecord);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "借阅信息更新失败";
        }
    }

    @Override
    public ReturnRecord getRR(String returnId) {
        return returnRecordMapper.selectByPrimaryKey(returnId);
    }

    @Override
    @Transactional
    public String deleteRR(String[] ids) {
        if (ids == null)
            return "归还记录错误";
        if (ids.length == 0)
            return "无选中归还记录";
        
        try {
            for (String id : ids)
                returnRecordMapper.deleteByPrimaryKey(id);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "归还记录删除失败";
        }
    }

    @Override
    @Transactional
    public String updateRR(ReturnRecord returnRecord) {
        if (returnRecord == null)
            return "归还信息错误";
        
        try {
            returnRecordMapper.updateByPrimaryKey(returnRecord);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "归还信息更新失败";
        }
    }

    @Override
    public FineRecord getFR(String fineId) {
        return fineRecordMapper.selectByPrimaryKey(fineId);
    }

    @Override
    @Transactional
    public String deleteFR(String[] ids) {
        if (ids == null)
            return "罚款记录错误";
        if (ids.length == 0)
            return "无选中罚款记录";
        
        try {
            for (String id : ids)
                fineRecordMapper.deleteByPrimaryKey(id);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "罚款记录删除失败";
        }
    }

    @Override
    @Transactional
    public String updateFR(FineRecord fineRecord) {
        if (fineRecord == null)
            return "罚款信息错误";

        try {
            fineRecordMapper.updateByPrimaryKey(fineRecord);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "罚款信息更新失败";
        }
    }

    //========================================================================================
    // UerBookList
    //========================================================================================
    @Override
    public UserBookListListDto getUserBookLists(UserBookListSearchDto userBookListSearchDto) {
        userBookListSearchDto.initial();
        return new UserBookListListDto(userBookListMapper.getUserBookLists(userBookListSearchDto),
                userBookListMapper.countUserBookList(userBookListSearchDto));
    }

    @Override
    public UserBookList getUserBookList(String userBookListId) {
        return userBookListMapper.selectByPrimaryKey(userBookListId);
    }

    @Override
    @Transactional
    public String addUserBookList(UserBookList userBookList) {
        if (userBookList == null)
            return "书单错误";
        userBookList.initial();
        while (bookMapper.selectByPrimaryKey(userBookList.getId()) != null)
            userBookList.setId(UUID.randomUUID().toString());

        try {
            userBookListMapper.insert(userBookList);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "书单添加失败";
        }
    }

    @Override
    @Transactional
    public String deleteUserBookList(String[] ids) {
        if (ids == null)
            return "书单错误";
        if (ids.length == 0)
            return "无选中书单";

        try {
            for (String id : ids)
                userBookListMapper.deleteByPrimaryKey(id);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "删除书单失败";
        }
    }

    @Override
    @Transactional
    public String updateUserBookList(UserBookList userBookList) {
        if(userBookList == null)
            return "书单错误";

        try{
            userBookListMapper.updateByPrimaryKey(userBookList);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "修改书单失败";
        }
    }

    //========================================================================================
    // Message
    //========================================================================================
    @Override
    public MessageListDto getMessages(MessageSearchDto messageSearchDto) {
        messageSearchDto.initial();
        return new MessageListDto(messageMapper.getMessages(messageSearchDto),
                messageMapper.countMessage(messageSearchDto));
    }

    @Override
    public Message getMessage(String messageId) {
        return messageMapper.selectByPrimaryKey(messageId);
    }

    @Override
    @Transactional
    public String addMessage(Message message) {
        if (message == null)
            return "通知错误";
        message.initial();
        while (messageMapper.selectByPrimaryKey(message.getId()) != null)
            message.setId(UUID.randomUUID().toString());

        try {
            messageMapper.insert(message);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "通知添加失败";
        }
    }

    @Override
    @Transactional
    public String deleteMessage(String[] ids) {
        if (ids == null)
            return "通知错误";
        if (ids.length == 0)
            return "无选中通知";

        try {
            for (String id : ids)
                messageMapper.deleteByPrimaryKey(id);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "删除通知失败";
        }
    }

    @Override
    @Transactional
    public String updateMessage(Message message) {
        if(message == null)
            return "通知错误";

        try{
            messageMapper.updateByPrimaryKey(message);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "修改通知失败";
        }
    }

    //========================================================================================
    // BookReview
    //========================================================================================
    @Override
    public BookReviewListDto getBookReviews(BookReviewSearchDto bookReviewSearchDto) {
        bookReviewSearchDto.initial();
        if (bookReviewSearchDto.getStatusStr() != null && !bookReviewSearchDto.getStatusStr().isEmpty())
            bookReviewSearchDto.setStatus(borrowRecordMapper.getStatusId(bookReviewSearchDto.getStatusStr()));
        return new BookReviewListDto(bookReviewMapper.getBookReviews(bookReviewSearchDto),
                bookReviewMapper.countBookReview(bookReviewSearchDto));
    }

    @Override
    public BookReview getBookReview(String bookReviewId) {
        return bookReviewMapper.selectByPrimaryKey(bookReviewId);
    }

    @Override
    @Transactional
    public String addBookReview(BookReview bookReview) {
        if (bookReview == null)
            return "书评错误";
        bookReview.initial();
        while (messageMapper.selectByPrimaryKey(bookReview.getId()) != null)
            bookReview.setId(UUID.randomUUID().toString());

        try {
            bookReviewMapper.insert(bookReview);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "书评添加失败";
        }
    }

    @Override
    @Transactional
    public String deleteBookReview(String[] ids) {
        if (ids == null)
            return "书评错误";
        if (ids.length == 0)
            return "无选中书评";

        try {
            for (String id : ids)
                bookReviewMapper.deleteByPrimaryKey(id);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "删除书评失败";
        }
    }

    @Override
    @Transactional
    public String updateBookReview(BookReview bookReview) {
        if(bookReview == null)
            return "书评错误";

        try{
            bookReviewMapper.updateByPrimaryKey(bookReview);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "修改书评失败";
        }
    }
}
