package xzz.library.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xzz.library.dao.*;
import xzz.library.dto.*;
import xzz.library.pojo.*;
import xzz.library.service.AdminService;

import java.util.ArrayList;
import java.util.List;
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
    //Users
    //========================================================================================
    @Override
    public UsersDto getUsers(Integer limit, Integer page) {
        if (limit == null)
            limit = 10;
        int start = page == null ? 0 : (page-1)*limit;
        List<User> users = userMapper.getUsers(limit, start);
        int total = userMapper.countUsers();
        return new UsersDto(users, total);
    }

    @Override
    public User getUser(String id) {
        return userMapper.selectByPrimaryKey(id);
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
        
        try{
            userMapper.insert(user);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "新增用户失败";
        }
    }

    @Override
    @Transactional
    public String deleteUser(String ids) {
        if (ids == null)
            return "用户错误";
        String[] idList = ids.split(",");
        if (idList.length == 0)
            return "无选中用户";
        
        try {
            for (String id : idList)
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
        
        try {
            userMapper.updateByPrimaryKey(user);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "修改失败";
        }
    }

    //========================================================================================
    //Books
    //========================================================================================
    @Override
    public BooksDto getBooks(BooksGetDto booksGetDto) {
        booksGetDto.initial();
        List<Book> books = bookMapper.getBookList(booksGetDto);
        int total = bookMapper.countBook(booksGetDto);
        return new BooksDto(books,total);
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
    public Book getBook(String id) {
        return bookMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public String deleteBook(String ids) {
        if (ids == null)
            return "图书错误";
        String[] idList = ids.split(",");
        if (idList.length == 0)
            return "无选中图书";
        
        try {
            for (String id : idList)
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
    //Records
    //========================================================================================
    @Override
    public RecordsDto getRecords(RecordsGetDto recordsGetDto) {
        recordsGetDto.initial();
        int limit = recordsGetDto.getLimit();
        int start = (recordsGetDto.getPage() - 1) * limit;
        List records = new ArrayList();
        int total = 0;
        switch (recordsGetDto.getRecordType()){
            case "borrow" :
                records = borrowRecordMapper.getRecords(limit, start);
                total = borrowRecordMapper.countRecords();
                break;
            case "return":
                records = returnRecordMapper.getRecords(limit, start);
                total = returnRecordMapper.countRecords();
                break;
            case "fine":
                records = fineRecordMapper.getRecords(limit, start);
                total = fineRecordMapper.countRecords();
                break;
            default:
        }
        return new RecordsDto(records, total);
    }

    @Override
    public BorrowRecord getBR(String id) {
        return borrowRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public String deleteBR(String ids) {
        if (ids == null)
            return "借阅记录错误";
        String[] idList = ids.split(",");
        if (idList.length == 0)
            return "无选中借阅记录";
        
        try {
            for (String id : idList)
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
    public ReturnRecord getRR(String id) {
        return returnRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public String deleteRR(String ids) {
        if (ids == null)
            return "归还记录错误";
        String[] idList = ids.split(",");
        if (idList.length == 0)
            return "无选中归还记录";
        
        try {
            for (String id : idList)
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
    public FineRecord getFR(String id) {
        return fineRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public String deleteFR(String ids) {
        if (ids == null)
            return "罚款记录错误";
        String[] idList = ids.split(",");
        if (idList.length == 0)
            return "无选中罚款记录";
        
        try {
            for (String id : idList)
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
