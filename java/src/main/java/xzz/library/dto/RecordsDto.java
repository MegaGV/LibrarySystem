package xzz.library.dto;

import java.util.List;

public class RecordsDto {
    private Integer total = 0;
    private List data;

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
