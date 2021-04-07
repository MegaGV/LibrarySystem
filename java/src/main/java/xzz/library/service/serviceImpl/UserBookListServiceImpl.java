package xzz.library.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xzz.library.dao.BookMapper;
import xzz.library.dao.UserBookListMapper;
import xzz.library.dto.*;
import xzz.library.pojo.Book;
import xzz.library.pojo.UserBookList;
import xzz.library.service.UserBookListService;
import xzz.library.util.StringListUtils;

import java.util.UUID;


@Service
public class UserBookListServiceImpl implements UserBookListService {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserBookListMapper userBookListMapper;

    @Override
    public UserBookListListDto getUserBookListList(String userId) {
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
            return "无法删除他人书单";

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
    public String updateBook(String userId, String bookId, String userBookListId, Boolean isAdd) {
        if (userId == null || bookId == null || userBookListId == null)
            return "信息有误";
        UserBookList dbList = userBookListMapper.selectByPrimaryKey(userBookListId);
        if (!dbList.getUserId().equals(userId))
            return "无法编辑他人书单";
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

}
