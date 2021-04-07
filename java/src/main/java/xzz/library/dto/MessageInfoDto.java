package xzz.library.dto;

import xzz.library.pojo.Message;

public class MessageInfoDto {
    //========================================================================================
    // Description: 后端返回到前端的通知信息类
    //========================================================================================
    private String id;

    private String userId;

    private String title;

    private String content;

    private String status;

    public MessageInfoDto(Message message){
        this.id = message.getId();
        this.userId = message.getUserId();
        this.title = message.getTitle();
        this.content = message.getContent();
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
