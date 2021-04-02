package xzz.library.pojo;

import java.io.Serializable;
import java.util.UUID;

public class UserBookList implements Serializable {

    private String id;

    private String userId;

    private String listName;

    private String description;

    private String books;

    private static final long serialVersionUID = 1L;

    public void initial(){
        this.id = UUID.randomUUID().toString();
        this.books = "";
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

    public String getBooks() {
        return books;
    }

    public void setBooks(String books) {
        this.books = books;
    }
}
