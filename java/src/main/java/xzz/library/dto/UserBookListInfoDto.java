package xzz.library.dto;

import xzz.library.pojo.Book;
import xzz.library.pojo.UserBookList;

import java.util.ArrayList;
import java.util.List;

public class UserBookListInfoDto {
    //========================================================================================
    // Description: 后端返回到前端的书单信息类
    //========================================================================================
    private String id;

    private String userId;

    private String listName;

    private String description;

    private List<Book> bookList;

    public UserBookListInfoDto(UserBookList userBookList){
        this.id = userBookList.getId();
        this.userId = userBookList.getUserId();
        this.listName = userBookList.getListName();
        this.description = userBookList.getDescription();
        this.bookList = new ArrayList<>();
    }

    public void addBook(Book book){
        this.bookList.add(book);
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

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
