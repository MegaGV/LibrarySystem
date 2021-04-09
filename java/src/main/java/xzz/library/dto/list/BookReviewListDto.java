package xzz.library.dto.list;

import xzz.library.dto.info.BookReviewInfoDto;

import java.util.List;

public class BookReviewListDto {
    //========================================================================================
    // Description: 后端返回到前端的书评列表类
    //========================================================================================
    private Integer total;

    private List data;

    public BookReviewListDto(List books, int total){
        this.data = books;
        this.total = total;
    }

    public BookReviewListDto(List books){
        this.data = books;
        this.total = books.size();
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
