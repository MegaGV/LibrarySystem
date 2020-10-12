package xzz.library.dto;

import java.util.List;

public class RecordsDto {
    private Integer total;
    private List data;

    public RecordsDto(List data, int total){
        this.data = data;
        this.total = total;
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
