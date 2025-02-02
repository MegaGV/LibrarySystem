package xzz.library.pojo;

import java.io.Serializable;
import java.util.UUID;

public class BookReview implements Serializable {

    private String id;

    private String userId;

    private String bookId;

    private String title;

    private String content;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public void initial() {
        this.id = UUID.randomUUID().toString();
        this.status = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
