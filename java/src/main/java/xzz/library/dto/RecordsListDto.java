package xzz.library.dto;

import java.util.List;

public class RecordsListDto {
    //========================================================================================
    // Description: 后端返回到前端的记录列表类
    //========================================================================================
    private Integer total;

    private List data;

    public RecordsListDto(List data, int total){
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
