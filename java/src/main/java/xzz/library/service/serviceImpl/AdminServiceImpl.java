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
import xzz.library.util.MessageUtils;

import java.util.Date;
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
    @Autowired
    private DiscussMapper discussMapper;
    @Autowired
    private CommentMapper commentMapper;

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

        Message firstMessage = MessageUtils.firstMessage(user.getId());

        try {
            userMapper.insert(user);
            messageMapper.insert(firstMessage);
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
        message.setReceiveDate(new Date());

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

    @Override
    @Transactional
    public String Examine(String reviewId, int status) {
        if(reviewId == null)
            return "书评错误";
        BookReview dbReview = bookReviewMapper.selectByPrimaryKey(reviewId);
        if (dbReview == null)
            return "书评不存在";

        dbReview.setStatus(status);
        Message message;
        String bookName = bookMapper.selectByPrimaryKey(dbReview.getBookId()).getBookName();
        switch (status){
            case 0:
                message = MessageUtils.ReviewReProcess(dbReview.getUserId(),bookName, dbReview.getTitle());
                break;
            case 1:
                message = MessageUtils.ReviewProcess(dbReview.getUserId(),bookName, dbReview.getTitle());
                break;
            case 2:
                message = MessageUtils.ReviewNotProcess(dbReview.getUserId(),bookName, dbReview.getTitle());
                break;
            default:return "状态错误";
        }

        try{
            bookReviewMapper.updateByPrimaryKey(dbReview);
            messageMapper.insert(message);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "审核失败";
        }
    }

    //========================================================================================
    // Discuss
    //========================================================================================
    @Override
    public DiscussListDto getDiscusses(DiscussSearchDto discussSearchDto) {
        discussSearchDto.initial();
        return new DiscussListDto(discussMapper.getDiscusses(discussSearchDto), discussMapper.countDiscusses(discussSearchDto));
    }

    @Override
    public Discuss getDiscuss(String discussId) {
        return discussMapper.selectByPrimaryKey(discussId);
    }

    @Override
    @Transactional
    public String addDiscuss(Discuss discuss) {
        if (discuss == null)
            return "讨论错误";
        discuss.initial();
        while (discussMapper.selectByPrimaryKey(discuss.getId()) != null)
            discuss.setId(UUID.randomUUID().toString());

        try{
            discussMapper.insert(discuss);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "讨论添加失败";
        }
    }

    @Override
    @Transactional
    public String deleteDiscuss(String[] ids) {
        if (ids == null)
            return "讨论错误";
        if (ids.length == 0)
            return "无选中讨论";

        try {
            for (String id : ids)
                discussMapper.deleteByPrimaryKey(id);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "删除讨论失败";
        }
    }

    @Override
    @Transactional
    public String updateDiscuss(Discuss discuss) {
        if(discuss == null)
            return "讨论错误";

        try{
            discussMapper.updateByPrimaryKey(discuss);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "修改讨论失败";
        }
    }

    @Override
    public CommentListDto getComments(CommentSearchDto commentSearchDto) {
        commentSearchDto.initial();
        return new CommentListDto(commentMapper.getComments(commentSearchDto), commentMapper.countComments(commentSearchDto));
    }

    @Override
    public Comment getComment(String commentId) {
        return commentMapper.selectByPrimaryKey(commentId);
    }

    @Override
    @Transactional
    public String addComment(Comment comment) {
        if (comment == null)
            return "信息有误";
        comment.initial();
        while (commentMapper.selectByPrimaryKey(comment.getId()) != null)
            comment.setId(UUID.randomUUID().toString());
        Discuss discuss = discussMapper.selectByPrimaryKey(comment.getDiscussId());
        Message message = MessageUtils.NewComment(discuss.getUserId(), discuss.getTitle());

        try{
            commentMapper.insert(comment);
            messageMapper.insert(message);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "评论添加失败";
        }
    }

    @Override
    @Transactional
    public String deleteComment(String[] ids) {
        if (ids == null)
            return "评论错误";
        if (ids.length == 0)
            return "无选中评论";

        try {
            for (String id : ids)
                commentMapper.deleteByPrimaryKey(id);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "删除评论失败";
        }
    }

    @Override
    @Transactional
    public String updateComment(Comment comment) {
        if(comment == null)
            return "评论错误";

        try{
            commentMapper.updateByPrimaryKey(comment);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "修改评论失败";
        }
    }

}
