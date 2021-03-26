package xzz.library.dto;

import xzz.library.pojo.UserBookList;

import java.util.List;

public class UserBookListDto {
    private Integer total;

    private List<UserBookList> data;

    public UserBookListDto(List<UserBookList> bookLists, int total){
        this.data = bookLists;
        this.total = total;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<UserBookList> getData() {
        return data;
    }

    public void setData(List<UserBookList> data) {
        this.data = data;
    }
}
