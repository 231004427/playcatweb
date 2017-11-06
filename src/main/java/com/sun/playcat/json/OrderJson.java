package com.sun.playcat.json;

import com.sun.playcat.dao.OrderDao;
import com.sun.playcat.domain.*;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by sunlin on 2017/8/16.
 */
public class OrderJson extends BaseJson {

    private OrderDao orderService;
    public OrderJson(WebApplicationContext context) {
        super(context);
        orderService=(OrderDao) context.getBean("orderService");
    }
    public String search(BaseRequest baseRequest){
        OrderList orderList=gson.fromJson(baseRequest.getData(),OrderList.class);
        if(orderList.getCount()<=0) {
            orderList.setCount(orderService.searchCount(orderList));
        }
        orderList.setList(orderService.search(orderList));
        BaseResult baseResult;
        baseResult = MessageHelp.BuildBaseResult(0,
                "", ActionType.ORDER_SEARCH, "获取成功", gson.toJson(orderList));
        return gson.toJson(baseResult);
    }
    public String insert(BaseRequest baseRequest){
        Order order=gson.fromJson(baseRequest.getData(),Order.class);

        int resultInt=orderService.insert(order);
        BaseResult baseResult=new BaseResult();
        if(resultInt>0){
            baseResult=MessageHelp.BuildBaseResult(0,
                    "", ActionType.ORDER_ADD,"添加成功",gson.toJson(order));
        }
        if(resultInt==-1){
            baseResult=MessageHelp.BuildBaseResult(1,
                    "", ActionType.ORDER_ADD,"库存不足",gson.toJson(order));
        }
        if(resultInt==-2){
            baseResult=MessageHelp.BuildBaseResult(2,
                    "", ActionType.ORDER_ADD,"余额不足",gson.toJson(order));
        }
        return gson.toJson(baseResult);
    }
}
