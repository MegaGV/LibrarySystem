package xzz.library.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import xzz.library.dao.UserMapper;
import xzz.library.util.MD5Utils;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
    @Autowired
    private UserMapper userMapper;

    private String id;

    private String username;

    private String password;

    private String nickname;

    private Integer role;

    private Integer borrowed;

    private Integer status;

    private Double balance;

    private static final long serialVersionUID = 1L;

    public void borrowBook(){
        this.borrowed++;
        checkStatus();
    }

    public void returnBook(){
        this.borrowed--;
        checkStatus();
    }

    public void getFine(Double fine){
        this.balance -= fine;
        checkStatus();
    }

    private void checkStatus(){
        // 状态优先级 冻结 > 欠费 > 上限 = 普通
        if (this.status != 3){
            if (this.balance < 0)
                this.status = 1;
            else
                this.status = this.borrowed < 5 ? 0 : 2;
        }
    }

    public void initial(){
        this.id = UUID.randomUUID().toString();
        while (userMapper.selectByPrimaryKey(this.id) != null)
            this.id = UUID.randomUUID().toString();
        this.role = 0;
        this.borrowed = 0;
        this.status = 0;
        this.balance = 0.00;
        this.password = MD5Utils.md5Code(this.username, this.password);
    }

    public void credit(Double money) {
        this.balance += money;
        checkStatus();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(Integer borrowed) {
        this.borrowed = borrowed;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}