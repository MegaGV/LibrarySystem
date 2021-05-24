package xzz.library.util;

import xzz.library.pojo.Message;

public class MessageUtils {
    public static Message firstMessage(String userId){
        Message message = new Message();
        message.initial();
        message.setUserId(userId);
        message.setTitle("欢迎使用本系统");
        message.setContent("欢迎使用本中山图书馆社区系统，有任何问题与建议可以在讨论区中提出");
        return message;
    }

    public static Message returnWarning(String userId, String bookName){
        Message message = new Message();
        message.initial();
        message.setUserId(userId);
        message.setTitle("借阅到期提醒");
        message.setContent("您借阅的书籍《" + bookName + "》即将到期，请留意归还日期及时归还");
        return message;
    }

    public static Message returnOverWarning(String userId, String bookName){
        Message message = new Message();
        message.initial();
        message.setUserId(userId);
        message.setTitle("借阅到期提醒");
        message.setContent("您借阅的书籍《" + bookName + "》已超过归还日期，请及时归还");
        return message;
    }

    public static Message ReviewProcess(String userId, String bookName, String reviewTitle){
        Message message = new Message();
        message.initial();
        message.setUserId(userId);
        message.setTitle("书评审核提醒");
        message.setContent("您为书籍《" + bookName + "》撰写的书评《" + reviewTitle + "》已通过审核");
        return message;
    }

    public static Message ReviewNotProcess(String userId, String bookName, String reviewTitle){
        Message message = new Message();
        message.initial();
        message.setUserId(userId);
        message.setTitle("书评审核提醒");
        message.setContent("您为书籍《" + bookName + "》撰写的书评《" + reviewTitle + "》未能通过审核");
        return message;
    }

    public static Message ReviewReProcess(String userId, String bookName, String reviewTitle){
        Message message = new Message();
        message.initial();
        message.setUserId(userId);
        message.setTitle("书评审核提醒");
        message.setContent("您为书籍《" + bookName + "》撰写的书评《" + reviewTitle + "》已发起重审，请耐心等待");
        return message;
    }

    public static Message NewComment(String userId, String discussTitle){
        Message message = new Message();
        message.initial();
        message.setUserId(userId);
        message.setTitle("新回复提醒");
        message.setContent("您的帖子《" + discussTitle + "》有新的回复了，请前往查看");
        return message;
    }
}
