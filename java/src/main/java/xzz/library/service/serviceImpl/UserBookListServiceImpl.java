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

import java.util.List;
import java.util.UUID;

@Service
public class UserBookListServiceImpl implements UserBookListService {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserBookListMapper userBookListMapper;


    @Override
    public UserBookListsDto getUserBookLists(String userId) {
        List<UserBookList> userBookLists = userBookListMapper.getBookListsByUserid(userId);
        return new UserBookListsDto(userBookLists);
    }

    @Override
    public UserBookListDto getListBooks(String userBookListId){
        UserBookList userBookList = userBookListMapper.selectByPrimaryKey(userBookListId);
        UserBookListDto userBookListDto = new UserBookListDto(userBookList);

        String[] bookIds = userBookList.getBooks().split(",");
        for (String bookId : bookIds){
            Book book = bookMapper.selectByPrimaryKey(bookId);
            if (book != null)
                userBookListDto.addBook(book);
            else // 有找不到的书，添加空对象标记
                userBookListDto.addBook(new Book());
        }
        return userBookListDto;
    }

    @Override
    @Transactional
    public String addUserBookList(UserBookList userBookList) {
        if (userBookList.getUserId() == null || userBookList.getListName() == null)
            return "信息有误，创建失败";
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
    public String deleteUserBookList(String userBookListId, String userId) {
        if (userBookListId == null)
            return "书单错误";
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
            return "书单错误";
        UserBookList dbList = userBookListMapper.selectByPrimaryKey(userBookList.getId());
        if (!dbList.getUserId().equals(userBookList.getUserId()))
            return "无法编辑他人书单";

        try{
            userBookListMapper.updateByPrimaryKey(userBookList);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return "删除书单失败";
        }
    }

}
