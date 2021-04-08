package xzz.library.dto.list;

import xzz.library.pojo.Book;

import java.util.List;

public class BookListDto {
    //========================================================================================
    // Description: 后端返回到前端的图书列表类
    //========================================================================================
    private Integer total;

    private List<Book> data;

    public BookListDto(List<Book> books, int total){
        this.data = books;
        this.total = total;
    }

    public BookListDto(List<Book> books){
        this.data = books;
        this.total = books.size();
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
