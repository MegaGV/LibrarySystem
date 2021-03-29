package xzz.library.dto;

import xzz.library.pojo.Book;
import xzz.library.pojo.UserBookList;

import java.util.ArrayList;
import java.util.List;

public class UserBookListDto {

    private String id;

    private String userId;

    private String listName;

    private List<Book> bookList;

    public UserBookListDto(UserBookList userBookList){
        this.id = userBookList.getId();
        this.userId = userBookList.getUserId();
        this.listName = userBookList.getListName();
        this.bookList = new ArrayList<>();
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

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
