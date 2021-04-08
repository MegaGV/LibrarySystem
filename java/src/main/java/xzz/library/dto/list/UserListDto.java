package xzz.library.dto.list;

import xzz.library.pojo.User;

import java.util.List;

public class UserListDto {
    //========================================================================================
    // Description: 后端返回到前端的用户列表类
    //========================================================================================
    private Integer total;
    
    private List<User> data;

    public UserListDto(List<User> users, int total){
        this.data = users;
        this.total = total;
    }

    public UserListDto(List<User> users){
        this.data = users;
        this.total = users.size();
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
