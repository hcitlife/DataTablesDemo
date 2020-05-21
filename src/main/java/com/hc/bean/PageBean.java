package com.hc.bean;

import java.util.List;

public class PageBean<T> {
    /**
     * 每页显示的条数
     */
    private long pageSize = 10;
    /**
     * 当前的页码
     */
    private long pageNum;
    /**
     * 一共有多少条记录
     */
    private long total;
    /**
     * 一共有多少页
     */
    private long pages;
    /**
     * 每一页所显示的数据
     */
    private List<T> records;

    public PageBean() {
    }

    public PageBean(long pageSize, long pageNum, long total, long pages, List<T> records) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.total = total;
        this.pages = pages;
        this.records = records;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}