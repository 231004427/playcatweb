package com.sun.playcat.domain;

import java.util.List;

/**
 * Created by sunlin on 2017/8/9.
 */
public class AddressList {
    int count;
    int start;
    int pageNum;
    int user_id;
    int status;
    List<Address> addressList;

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public int getCount() {
        return count;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getStart() {
        return start;
    }

    public int getUser_id() {
        return user_id;
    }

    public List<Address> getAddressList() {
        return addressList;
    }
}
