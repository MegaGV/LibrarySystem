package xzz.library.dto.search;

public class RecordSearchDto {
    //========================================================================================
    // Description: 前端发送到后端形成的记录检索类
    //========================================================================================
    private String recordType;

    private String userId;

    private String statusStr;

    private Integer status;

    private Integer limit;

    private Integer page;

    public void initial() {
        if (this.limit == null)
            this.limit = 5;
        // page作首个开始查找的对象，即start
        if (this.page == null)
            this.page = 0;
        else
            this.page = (this.page - 1) * this.limit;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}
