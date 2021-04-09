package xzz.library.dto.info;

import xzz.library.pojo.Book;
import xzz.library.pojo.BookReview;
import xzz.library.pojo.User;

public class BookReviewInfoDto {
    //========================================================================================
    // Description: 后端返回到前端的书评信息类
    //========================================================================================
    private String id;

    private User user;

    private Book book;

    private String title;

    private String content;

    private String status;

    public BookReviewInfoDto(BookReview bookReview){
        this.id = bookReview.getId();
        this.title = bookReview.getTitle();
        this.content = bookReview.getContent();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
