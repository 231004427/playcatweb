package com.sun.playcat.dao;

import com.sun.playcat.domain.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by sunlin on 2017/8/9.
 */
public interface AddressDao {
    public int insert(Address address);
    public Address get(Address address);
    public void del(Address address);
    public void update(Address address);
    public int searchCount(@Param("user_id") int userId,
                           @Param("status") int status);
    public List<Address> search(
            @Param("user_id") int userId,
            @Param("status") int status,
            @Param("start") int start,
            @Param("pageNum") int pageNum);
}
