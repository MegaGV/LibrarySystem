package xzz.library.dto.list;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import xzz.library.pojo.Book;
import xzz.library.pojo.BorrowRecord;

import java.util.Date;

public class BorrowRecordListDto {
    //========================================================================================
    // Description: 后端返回到前端的借阅记录列表类
    //========================================================================================
    private String id;

    private String userId;

    private Book book;

    @DateTimeFormat(pattern = "yyy-MM-dd hh:mm:sss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:sss")
    private Date borrowDate;

    @DateTimeFormat(pattern = "yyy-MM-dd hh:mm:sss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:sss")
    private Date returnDate;

    private String status;

    public BorrowRecordListDto(BorrowRecord borrowRecord){
        this.id = borrowRecord.getId();
        this.userId = borrowRecord.getUserId();
        this.borrowDate = borrowRecord.getBorrowDate();
        this.returnDate = borrowRecord.getReturnDate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
