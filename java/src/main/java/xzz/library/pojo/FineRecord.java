package xzz.library.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import xzz.library.dao.FineRecordMapper;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

public class FineRecord implements Serializable {

    private String id;

    private String borrowId;

    private String userId;

    private String returnId;

    private Integer days;

    private Double fine;

    private static final long serialVersionUID = 1L;

    public FineRecord(){

    }

    public FineRecord(BorrowRecord borrowRecord, ReturnRecord returnRecord, Double price){
        this.id = UUID.randomUUID().toString();
        this.userId = borrowRecord.getUserId();
        this.borrowId = borrowRecord.getId();
        this.returnId = returnRecord.getId();
        this.days = getDaysBetween(borrowRecord.getReturnDate(), returnRecord.getReturnDate());
        this.fine = price / 100 * this.days;
    }

    private int getDaysBetween (Date shouldReturn, Date realReturn){
        Calendar d1 = new GregorianCalendar();
        Calendar d2 = new GregorianCalendar();
        d1.setTime(shouldReturn);
        d2.setTime(realReturn);

        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2){
            d1 = (Calendar) d1.clone();
            do{
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);//得到当年的实际天数
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
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

    public String getReturnId() {
        return returnId;
    }

    public void setReturnId(String returnId) {
        this.returnId = returnId == null ? null : returnId.trim();
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Double getFine() {
        return fine;
    }

    public void setFine(Double fine) {
        this.fine = fine;
    }
}