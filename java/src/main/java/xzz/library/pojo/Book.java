package xzz.library.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import xzz.library.dao.BookMapper;

import java.io.Serializable;
import java.util.UUID;

public class Book implements Serializable {

    private String id;

    private String bookName;

    private String bookType;

    private String author;

    private String publisher;

    private Double price;

    private Integer stock;

    private Integer total;

    private String detail;

    private static final long serialVersionUID = 1L;

    public void borrowBook(){
        this.stock--;
    }

    public void returnBook(){
        this.stock++;
    }

    public void initial(){
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType == null ? null : bookType.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}