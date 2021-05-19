package xzz.library.dto.list;

import xzz.library.pojo.Comment;

import java.util.List;

public class CommentListDto {
    //========================================================================================
    // Description: 后端返回到前端的评论列表类
    //========================================================================================
    private Integer total;

    private List<Comment> data;

    public CommentListDto(List<Comment> books, int total){
        this.data = books;
        this.total = total;
    }

    public CommentListDto(List<Comment> books){
        this.data = books;
        this.total = books.size();
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Comment> getData() {
        return data;
    }

    public void setData(List<Comment> data) {
        this.data = data;
    }
}
