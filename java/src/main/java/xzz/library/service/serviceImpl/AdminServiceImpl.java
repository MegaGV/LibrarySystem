package xzz.library.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xzz.library.dao.BookMapper;
import xzz.library.dao.UserMapper;
import xzz.library.dto.BooksDto;
import xzz.library.dto.UsersDto;
import xzz.library.pojo.Book;
import xzz.library.pojo.User;
import xzz.library.service.AdminService;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BookMapper bookMapper;

    //========================================================================================
    //Users
    //========================================================================================
    @Override
    public UsersDto getUsers(Integer limit, Integer page) {
        if (limit==null){
            limit=10;
        }
        int start = 0;
        if (page != null) {
            start = (page-1)*limit;
        }
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
        if (userMapper.getUserByUsername(user.getUsername()) != null){
            return "用户名重复";
        }
        user.initial();
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
    public String deleteUser(String[] ids) {
        if (ids == null)
            return "用户错误";
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
    public BooksDto getBooks(Book book, Integer limit, Integer page) {
        if (limit == null)
            limit = 10;
        int start = 0;
        if (page != null) {
            start = (page-1)*limit;
        }
        if (book == null)
            book = new Book();
        List<Book> books = bookMapper.getBookList(book.getBookName(), book.getBookType(), book.getAuthor(),
                book.getPublisher(),book.getStock(), limit, start);
        int total = bookMapper.countBook(book.getBookName(), book.getBookType(), book.getAuthor(),
                book.getPublisher(),book.getStock());
        return new BooksDto(books,total);
    }

    @Override
    @Transactional
    public String addBook(Book book) {
        if (book == null)
            return "图书错误";
        book.initial();
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
    public String deleteBook(String[] ids) {
        if (ids == null)
            return "图书错误";
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
    public String updateBook(Book book) {
        if(book == null)
            return "图书错误";
        try{
            bookMapper.updateByPrimaryKey(book);
        }catch (Exception e){
            e.printStackTrace();
            return "修改图书失败";
        }
    }

}
