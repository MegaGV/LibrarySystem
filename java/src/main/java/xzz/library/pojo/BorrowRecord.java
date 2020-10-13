package xzz.library.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import xzz.library.dao.BorrowRecordMapper;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

public class BorrowRecord implements Serializable {

    private String id;

    private String userId;

    private String bookId;

    @DateTimeFormat(pattern = "yyy-MM-dd hh:mm:sss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:sss")
    private Date borrowDate;

    @DateTimeFormat(pattern = "yyy-MM-dd hh:mm:sss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:sss")
    private Date returnDate;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public BorrowRecord(String userId, String bookId){
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(this.borrowDate);
        calendar.add(Calendar.DATE,30);
        this.returnDate = calendar.getTime();
        this.status = 0;
    }

    public void returnBook(){
        this.status = 2;
        this.returnDate = new Date();
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

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId == null ? null : bookId.trim();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}