package com.oliwen.entity;


public class Page {

    /**
     * 每页显示记录数
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 总记录数
     */
    private int totalResult;
    /**
     * 当前页
     */
    private int pageIndex;
    /**
     * 当前记录起始索引
     */
    private int pageResult;

    private boolean checkTotal;
    private PageData pd = new PageData();

    public Page(int pageIndex,int pageSize) {
        this.setPageSize(pageSize);
        this.setPageIndex(pageIndex);
    }

    public Page() {
        this.setPageSize(18);
        this.setPageIndex(1);
    }

    public Page(Integer pageIndex,Integer pageSize) {
        if (pageIndex == null || pageIndex == 0) {
            pageIndex = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 18;
        }
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageResult() {
        return this.pageResult = (pageIndex - 1) * pageSize;
    }

    public void setPageResult(int pageResult) {
        this.pageIndex = pageResult;
    }

    public PageData getPd() {
        return pd;
    }

    public void setPd(PageData pd) {
        this.pd = pd;
    }

    public boolean getCheckTotal() {
        return checkTotal;
    }

    public void setCheckTotal(boolean checkTotal) {
        this.checkTotal = checkTotal;
    }
}
