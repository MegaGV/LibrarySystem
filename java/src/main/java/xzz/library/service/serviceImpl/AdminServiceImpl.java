package xzz.library.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xzz.library.dao.*;
import xzz.library.dto.*;
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

    //========================================================================================
    // Users
    //========================================================================================
    @Override
    public UsersListDto getUsers(Integer limit, Integer page) {
        if (limit == null)
            limit = 5;
        int start = page == null ? 0 : (page - 1) * limit;

        return new UsersListDto(userMapper.getUsers(limit, start), userMapper.countUsers());
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
    // Books
    //========================================================================================
    @Override
    public BooksListDto getBooks(BooksSearchInfoDto booksSearchInfoDto) {
        booksSearchInfoDto.initial();
        return new BooksListDto(bookMapper.getBookList(booksSearchInfoDto),bookMapper.countBook(booksSearchInfoDto));
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
    // Records
    //========================================================================================
    @Override
    public RecordsListDto getRecords(Integer limit, Integer page, String recordType){
        if (limit == null)
            limit = 5;
        int start = page == null ? 0 : (page - 1) * limit;
        switch (recordType){
            case "borrow" :
                return new RecordsListDto(borrowRecordMapper.getRecords(limit, start),
                        borrowRecordMapper.countRecords());
            case "return":
                return new RecordsListDto(returnRecordMapper.getRecords(limit, start),
                        returnRecordMapper.countRecords());
            case "fine":
                return new RecordsListDto(fineRecordMapper.getRecords(limit, start),
                        fineRecordMapper.countRecords());
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
}
