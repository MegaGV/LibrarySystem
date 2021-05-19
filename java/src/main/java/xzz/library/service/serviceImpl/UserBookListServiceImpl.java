package xzz.library.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xzz.library.dao.BookMapper;
import xzz.library.dao.UserBookListMapper;
import xzz.library.dto.info.UserBookListInfoDto;
import xzz.library.dto.list.BookListDto;
import xzz.library.dto.list.UserBookListListDto;
import xzz.library.dto.search.UserBookListSearchDto;
import xzz.library.pojo.Book;
import xzz.library.pojo.UserBookList;
import xzz.library.service.UserBookListService;
import xzz.library.util.StringListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserBookListServiceImpl implements UserBookListService {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserBookListMapper userBookListMapper;

    @Override
    public UserBookListListDto getUserBookLists(String userId) {
        return new UserBookListListDto(userBookListMapper.getBookListsByUserid(userId));
    }

    @Override
    public UserBookListInfoDto getUserBookList(String userBookListId){
        UserBookList userBookList = userBookListMapper.selectByPrimaryKey(userBookListId);
        UserBookListInfoDto userBookListInfoDto = new UserBookListInfoDto(userBookList);

        String[] bookIds = userBookList.getBooks().split(",");
        for (String bookId : bookIds){
            Book book = bookMapper.selectByPrimaryKey(bookId);
            if (book != null)
                userBookListInfoDto.addBook(book);
            else // 有找不到的书，添加空对象标记
                userBookListInfoDto.addBook(new Book());
        }
        return userBookListInfoDto;
    }

    @Override
    @Transactional
    public String addUserBookList(UserBookList userBookList) {
        if (userBookList == null || userBookList.getUserId() == null || userBookList.getListName() == null)
            return "信息有误";
        userBookList.initial();
        while (userBookListMapper.selectByPrimaryKey(userBookList.getId()) != null)
            userBookList.setId(UUID.randomUUID().toString());

        try{
            userBookListMapper.insert(userBookList);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "新增书单失败";
        }
    }

    @Override
    @Transactional
    public String deleteUserBookList(String userId, String userBookListId) {
        if (userId == null || userBookListId == null)
            return "信息有误";
        UserBookList userBookList = userBookListMapper.selectByPrimaryKey(userBookListId);
        if (!userId.equals(userBookList.getUserId()))
            return "权限不足";

        try{
            userBookListMapper.deleteByPrimaryKey(userBookListId);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "删除书单失败";
        }
    }

    @Override
    @Transactional
    public String updateUserBookList(UserBookList userBookList) {
        if (userBookList == null)
            return "信息有误";

        try{
            userBookListMapper.updateByPrimaryKey(userBookList);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "编辑书单失败";
        }
    }

    @Override
    @Transactional
    public String updateBook(String bookId, String userBookListId, Boolean isAdd) {
        if (bookId == null || userBookListId == null)
            return "信息有误";
        UserBookList dbList = userBookListMapper.selectByPrimaryKey(userBookListId);
        String books = dbList.getBooks();
        if (isAdd) {
            if (StringListUtils.count(books) >= 9) // 暂时限制书单长度为10
                return "书单书目达到上限";
            if (bookMapper.selectByPrimaryKey(bookId) == null)
                return "图书不存在";
            if (books.contains(bookId))
                return "该书已在书单中";
            books = StringListUtils.addOne(books, bookId);
        }
        else {
            if (books.isEmpty())
                return "书单为空";
            if (!books.contains(bookId))
                return "该书不在书单中";
            books = StringListUtils.removeOne(books, bookId);
        }

        try{
            userBookListMapper.updateBooksByPrimaryKey(userBookListId, books);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "书单图书变更失败";
        }
    }

    @Override
    public BookListDto getListBooks(String userBookListId) {
        UserBookList dbList = userBookListMapper.selectByPrimaryKey(userBookListId);
        if (dbList.getBooks().isEmpty())
            return new BookListDto(new ArrayList<>(), 0);
        String[] bookIds = dbList.getBooks().split(",");
        List<Book> books = new ArrayList<>();
        for (String bookId : bookIds) {
            Book book = bookMapper.selectByPrimaryKey(bookId);
            if (book != null)
                books.add(book);
            else // 有找不到的书，添加空对象标记
            {
                Book emptyBook = new Book();
                emptyBook.setId(bookId);
                books.add(emptyBook);
            }
        }
        return new BookListDto(books);
    }

    @Override
    public UserBookListListDto getAllUserBookLists(UserBookListSearchDto userBookListSearchDto) {
        userBookListSearchDto.initial();
        return new UserBookListListDto(userBookListMapper.getUserBookLists(userBookListSearchDto),
                userBookListMapper.countUserBookList(userBookListSearchDto));
    }

}
