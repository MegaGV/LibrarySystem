package xzz.library.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import xzz.library.dao.ReturnRecordMapper;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class ReturnRecord implements Serializable {
    @Autowired
    private ReturnRecordMapper returnRecordMapper;

    private String id;

    private String borrowId;

    @DateTimeFormat(pattern = "yyy-MM-dd hh:mm:sss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:sss")
    private Date returnDate;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public ReturnRecord(BorrowRecord borrowRecord){
        this.id = UUID.randomUUID().toString();
        while (returnRecordMapper.selectByPrimaryKey(this.id) != null)
            this.id = UUID.randomUUID().toString();
        this.borrowId = borrowRecord.getId();
        this.returnDate = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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