package xzz.library.dto;

public class RecordsGetDto {
    Integer limit;
    Integer page;
    String recordType;

    public void initial(){
        if (this.limit == null)
            this.limit = 10;
        // page作首个开始查找的对象，即start
        if (this.page == null)
            this.page = 0;
        else
            this.page = (this.page - 1) * this.limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }
}
