package xzz.library.dto.search;

public class CommentSearchDto {
    //========================================================================================
    // Description: 前端发送到后端形成的评论检索类
    //========================================================================================

    private String discussId;

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

    public String getDiscussId() {
        return discussId;
    }

    public void setDiscussId(String discussId) {
        this.discussId = discussId;
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
