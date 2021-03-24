package xzz.library.dto;

public class CreditInfoDto {
    //========================================================================================
    // Description: 前端返回到后端的充值信息类
    //========================================================================================
    String userId;

    Double money;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
