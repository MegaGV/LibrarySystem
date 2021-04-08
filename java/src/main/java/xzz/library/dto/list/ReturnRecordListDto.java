package xzz.library.dto.list;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import xzz.library.pojo.ReturnRecord;

import java.util.Date;

public class ReturnRecordListDto {
    //========================================================================================
    // Description: 后端返回到前端的归还记录列表类
    //========================================================================================
    private String id;

    private String borrowId;

    @DateTimeFormat(pattern = "yyy-MM-dd hh:mm:sss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:sss")
    private Date returnDate;

    private String status;

    public ReturnRecordListDto(ReturnRecord returnRecord){
        this.id = returnRecord.getId();
        this.borrowId = returnRecord.getBorrowId();
        this.returnDate = returnRecord.getReturnDate();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
