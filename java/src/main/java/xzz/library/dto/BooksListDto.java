package xzz.library.dto;

import xzz.library.pojo.Book;

import java.util.List;

public class BooksListDto {
    //========================================================================================
    // Description: 后端返回到前端的图书列表类
    //========================================================================================
    private Integer total;

    private List<Book> data;

    public BooksListDto(List<Book> books, int total){
        this.data = books;
        this.total = total;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Book> getData() {
        return data;
    }

    public void setData(List<Book> data) {
        this.data = data;
    }
}
