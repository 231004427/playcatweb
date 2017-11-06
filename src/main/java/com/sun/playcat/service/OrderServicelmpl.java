package com.sun.playcat.service;

import com.sun.playcat.dao.GoodsDao;
import com.sun.playcat.dao.OrderDao;
import com.sun.playcat.dao.UserDao;
import com.sun.playcat.domain.Goods;
import com.sun.playcat.domain.Order;
import com.sun.playcat.domain.OrderList;
import com.sun.playcat.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by sunlin on 2017/8/16.
 */
@Service("orderService")
@Transactional
public class OrderServicelmpl implements OrderDao {
    @Resource
    private OrderDao orderDao;
    @Resource
    private UserDao userDao;
    @Resource
    private GoodsDao goodsDao;
    @Override
    public int insert(Order order) {
        //获取商品
        Goods goods=new Goods();
        goods=goodsDao.selectPrice(order.getGoods_id());
        //订单金额

        float total=(float)(Math.round(goods.getPrice()*order.getNum()*100)/100);
        order.setPrice(total);
        //商品标题
        order.setGoods_title(goods.getTitle());
        //商品图片
        order.setGoods_img(goods.getHead_img());
        //是否有库存
        if(goods.getStock()>0||goods.getStock()==-1) {

            //设置订单创建时间,支付时间
            order.setCreate_time(new Date());
            order.setPay_time(new Date());
            if(goods.getGoods_value()>0) {
                order.setGoods_value(goods.getGoods_value() * order.getNum());
            }
            if (order.getType() == 1) {
                userDao.addZuan(order.getUser_id(),order.getGoods_value());
                //生成待支付订单
                order.setStatus(1);
                return orderDao.insert(order);
            } else{
                //获取余额
                User user = userDao.getMoney(order.getUser_id());
                int totalGoods = order.getNum() * goods.getPrice();
                //使用钻石兑换金币
                if (order.getType() == 2) {
                    if (totalGoods <= user.getZhuan()) {
                        //扣除余额
                        userDao.subZuan(order.getUser_id(),totalGoods);
                        //增加金币
                        userDao.addGold(order.getUser_id(),order.getGoods_value());
                        //订单完成
                        order.setStatus(4);
                        return orderDao.insert(order);
                    } else {
                        return -2;
                    }
                }
                //使用金币兑换流量、Q币、实物
                if (order.getType() == 3 || order.getType() == 4 || order.getType() == 5) {
                    if (totalGoods <= user.getGold()) {
                        userDao.subGold(order.getUser_id(),totalGoods);
                        order.setStatus(2);//已支付
                        return orderDao.insert(order);
                    } else {
                        return -2;
                    }
                }
            }
        }
        return -1;
    }

    @Override
    public int searchCount(OrderList orderList) {
        return orderDao.searchCount(orderList);
    }
    @Override
    public List<Order> search(OrderList orderList) {
        return orderDao.search(orderList);
    }

    @Override
    public int orderCount(@Param("user_id") int user_id) {
        return orderDao.orderCount(user_id);
    }
}
