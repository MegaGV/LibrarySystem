package xzz.library.dto.search;

public class UserBookListSearchDto {
    //========================================================================================
    // Description: 前端发送到后端形成的书单检索类
    //========================================================================================
    private String listName;

    private String userId;

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

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
