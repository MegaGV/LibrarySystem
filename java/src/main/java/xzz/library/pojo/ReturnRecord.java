package xzz.library.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class ReturnRecord implements Serializable {

    private String id;

    private String userId;

    private String borrowId;

    @DateTimeFormat(pattern = "yyy-MM-dd hh:mm:sss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:sss")
    private Date returnDate;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public void initial(BorrowRecord borrowRecord){
        this.id = UUID.randomUUID().toString();
        this.userId = borrowRecord.getUserId();
        this.borrowId = borrowRecord.getId();
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
        this.userId = userId;
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId == null ? null : borrowId.trim();
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