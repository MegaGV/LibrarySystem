package xzz.library.dto.list;

import xzz.library.pojo.Discuss;

import java.util.List;

public class DiscussListDto {
    //========================================================================================
    // Description: 后端返回到前端的帖子列表类
    //========================================================================================
    private Integer total;

    private List<Discuss> data;

    public DiscussListDto(List<Discuss> books, int total){
        this.data = books;
        this.total = total;
    }

    public DiscussListDto(List<Discuss> books){
        this.data = books;
        this.total = books.size();
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Discuss> getData() {
        return data;
    }

    public void setData(List<Discuss> data) {
        this.data = data;
    }
}
