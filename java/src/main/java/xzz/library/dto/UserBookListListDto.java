package xzz.library.dto;

import xzz.library.pojo.UserBookList;

import java.util.List;

public class UserBookListListDto {
    //========================================================================================
    // Description: 后端返回到前端的书单列表类
    //========================================================================================
    private Integer total;

    private List<UserBookList> data;

    public UserBookListListDto(List<UserBookList> bookLists, int total){
        this.data = bookLists;
        this.total = total;
    }

    public UserBookListListDto(List<UserBookList> bookLists){
        this.data = bookLists;
        this.total = bookLists.size();
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
