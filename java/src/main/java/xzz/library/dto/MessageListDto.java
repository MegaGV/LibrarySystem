package xzz.library.dto;

import xzz.library.pojo.Message;

import java.util.List;

public class MessageListDto {
    //========================================================================================
    // Description: 后端返回到前端的通知列表类
    //========================================================================================
    private Integer total;

    private List<Message> data;

    public MessageListDto(List<Message> messages, int total){
        this.data = messages;
        this.total = total;
    }

    public MessageListDto(List<Message> messages){
        this.data = messages;
        this.total = messages.size();
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Message> getData() {
        return data;
    }

    public void setData(List<Message> data) {
        this.data = data;
    }
}
