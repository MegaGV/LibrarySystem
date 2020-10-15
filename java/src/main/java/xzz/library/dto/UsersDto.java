package xzz.library.dto;

import xzz.library.pojo.User;

import java.util.List;

public class UsersDto {
    private Integer total;
    private List<User> data;

    public UsersDto(List<User> users, int total){
        this.data = users;
        this.total = total;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }
}
