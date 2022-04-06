package com.cjj.www.pojo;

public class Paging {
    /*
    分页需要的数据：
    需要进行分页的页面：
    1、首页
    2、各个分区的页面
    ------
    数据：
    pageNO 当前页面的页码
    pageSize 一页显示多少个数据
    pageTotal 总页码
     */

    private Integer pageNo;
    private Integer pageTotal;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return 4;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }
}
