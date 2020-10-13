package xzz.library.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xzz.library.dao.*;
import xzz.library.dto.BooksDto;
import xzz.library.dto.BooksGetDto;
import xzz.library.pojo.*;
import xzz.library.service.BookService;
import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BorrowRecordMapper borrowRecordMapper;
    @Autowired
    private ReturnRecordMapper returnRecordMapper;
    @Autowired
    private FineRecordMapper fineRecordMapper;

    @Override
    public BooksDto getBookList(BooksGetDto booksGetDto) {
        if (booksGetDto.getLimit() == null)
            booksGetDto.setLimit(10);
        int start = booksGetDto.getPage() == null ? 0 : (booksGetDto.getPage()-1)*booksGetDto.getLimit();
        List<Book> books = bookMapper.getBookList(booksGetDto.getBookName(), booksGetDto.getBookType(), booksGetDto.getAuthor(),
                booksGetDto.getPublisher(),booksGetDto.getStock(), booksGetDto.getLimit(), start);
        int total = bookMapper.countBook(booksGetDto.getBookName(), booksGetDto.getBookType(), booksGetDto.getAuthor(),
                booksGetDto.getPublisher(),booksGetDto.getStock());
        return new BooksDto(books,total);
    }

    @Override
    public Book getBookDetail(String bookId) {
        return bookMapper.selectByPrimaryKey(bookId);
    }

    @Override
    @Transactional
    public String borrowBook(String userId, String bookId) {
        User user = userMapper.selectByPrimaryKey(userId);
        Book book = bookMapper.selectByPrimaryKey(bookId);
        if (user == null)
            return "用户错误";
        if (book == null)
            return "图书错误";
        switch (user.getStatus()){
            case 0:
                if (book.getStock() == 0)
                    return "该书已无库存，无法借阅";
                BorrowRecord borrowRecord = new BorrowRecord(userId, bookId);
                while (borrowRecordMapper.selectByPrimaryKey(borrowRecord.getId()) != null)
                    borrowRecord.setId(UUID.randomUUID().toString());
                book.borrowBook();
                user.borrowBook();
                try{
                    borrowRecordMapper.insert(borrowRecord);
                    bookMapper.updateByPrimaryKey(book);
                    userMapper.updateByPrimaryKey(user);
                    return null;
                }catch (Exception e){
                    e.printStackTrace();
                    return "借阅失败";
                }
            case 1:
                return "您的账号尚有欠费，无法借阅";
            case 2:
                return "您的借书数已达上限，无法借阅";
            case 3:
                return "您的账号已被冻结，无法借阅";
            default:
        }
        return "未知错误";
    }

    @Override
    @Transactional
    public String returnBook(String borrowId) {
        FineRecord fineRecord = null;
        BorrowRecord borrowRecord = borrowRecordMapper.selectByPrimaryKey(borrowId);
        if (borrowRecord == null)
            return "借阅记录错误";
        Book book = bookMapper.selectByPrimaryKey(borrowRecord.getBookId());
        User user = userMapper.selectByPrimaryKey(borrowRecord.getUserId());
        if (user == null)
            return "用户错误";
        if (book == null)
            return "图书错误";
        int borrowRecordStatus = borrowRecord.getStatus();
        if (borrowRecordStatus == 2)
            return "该借阅已归还";
        else{
            ReturnRecord returnRecord = new ReturnRecord(borrowRecord);
            while (returnRecordMapper.selectByPrimaryKey(returnRecord.getId()) != null)
                returnRecord.setId(UUID.randomUUID().toString());
            if(borrowRecordStatus == 0)
                returnRecord.setStatus(0);
            else if (borrowRecordStatus == 1){
                returnRecord.setStatus(1);
                fineRecord = new FineRecord(borrowRecord, returnRecord, book.getPrice());
                while (fineRecordMapper.selectByPrimaryKey(fineRecord.getId()) != null)
                    fineRecord.setId(UUID.randomUUID().toString());
                user.getFine(fineRecord.getFine());
            }
            else
                return "未知错误";
            borrowRecord.returnBook();
            book.returnBook();
            user.returnBook();
            try{
                returnRecordMapper.insert(returnRecord);
                if (fineRecord != null)
                    fineRecordMapper.insert(fineRecord);
                borrowRecordMapper.updateByPrimaryKey(borrowRecord);
                bookMapper.updateByPrimaryKey(book);
                userMapper.updateByPrimaryKey(user);
                return null;
            }
            catch (Exception e){
                e.printStackTrace();
                return "归还失败";
            }
        }

    }

}
