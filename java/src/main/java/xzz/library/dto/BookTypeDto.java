package xzz.library.dto;

import xzz.library.pojo.BookType;

import java.util.List;

public class BookTypeDto {
    private Integer total;
    private List<BookType> data;

    public BookTypeDto(List<BookType> bookTypes, int total){
        this.data = bookTypes;
        this.total = total;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<BookType> getData() {
        return data;
    }

    public void setData(List<BookType> data) {
        this.data = data;
    }
}
