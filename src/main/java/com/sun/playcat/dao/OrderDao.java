package com.sun.playcat.dao;

import com.sun.playcat.domain.Order;
import com.sun.playcat.domain.OrderList;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * Created by sunlin on 2017/8/16.
 */
@MapperScan
public interface OrderDao {
    public int insert(Order order);
    public int searchCount(OrderList orderList);
    public List<Order> search(OrderList orderList);
    public int orderCount(@Param("user_id") int user_id);
}
