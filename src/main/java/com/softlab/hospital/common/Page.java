package com.softlab.hospital.common;

/**
 * Created by LiXiwen on 2019/7/3 17:56.
 **/
public class Page {
    /**
     * 当前页码
     */
    private Integer nowPage;

    /**
     * 总页码数
     */
    private Integer totalPage;

    /**
     * 每页数据量,默认 10
     */
    private Integer pageSize = 10;

    /**
     * 数据总量
     */
    private Integer totalSize;

    public Integer getNowPage() {
        return nowPage;
    }

    public void setNowPage(Integer nowPage) {
        this.nowPage = nowPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }
}
