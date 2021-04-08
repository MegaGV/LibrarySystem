package xzz.library.dto.info;

import xzz.library.pojo.User;

public class UserInfoDto {
    //========================================================================================
    // Description: 后端返回到前端的用户类
    //========================================================================================
    private String username;

    private String nickname;

    private String role;

    private Integer borrowed;

    private String status;

    private Double balance;

    public UserInfoDto(User user){
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.borrowed = user.getBorrowed();
        this.balance = user.getBalance();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(Integer borrowed) {
        this.borrowed = borrowed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
