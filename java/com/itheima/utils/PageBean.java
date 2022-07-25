package com.itheima.utils;

import java.util.List;

public class PageBean<T> {
    //当前页
    private int pageNum;
    //每页显示的数据条数
    private int pageSize;
    //总页数
    private int totalPage;
    //总记录数
    private int totalRecord;
    //分页数据
    List<T> data;

    public List<T> getData()
    {
        return  data;
    }
    public void setData( List<T> data)
    {
        this.data=data;
    }
    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
    public static<T> PageBean<T> getPageBean(int pageNum,int pageSize,int totalRecord,List<T> data) {

        PageBean<T> pageBean = new PageBean<>();
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalRecord(totalRecord);
        pageBean.setTotalPage((int) Math.ceil(totalRecord * 1.0 / pageSize));
        pageBean.setData(data);
        return pageBean;
    }
}
