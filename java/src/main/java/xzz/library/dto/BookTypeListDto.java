package xzz.library.dto;

import xzz.library.pojo.BookType;

import java.util.List;

public class BookTypeListDto {
    //========================================================================================
    // Description: 后端返回到前端的图书种类列表类
    //========================================================================================
    private Integer total;

    private List<BookType> data;

    public BookTypeListDto(List<BookType> bookTypes, int total){
        this.data = bookTypes;
        this.total = total;
    }

    public BookTypeListDto(List<BookType> bookTypes){
        this.data = bookTypes;
        this.total = bookTypes.size();
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
