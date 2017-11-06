import com.sun.playcat.dao.OrderDao;
import com.sun.playcat.domain.Order;
import com.sun.playcat.domain.OrderList;
import net.sf.ehcache.search.expression.Or;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by sunlin on 2017/8/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class OrderTest {
    @Resource
    private OrderDao orderService;
    @Test
    public void instert(){
        Order order=new Order();
        order.setUser_id(10012);
        order.setGoods_id(1);//30个钻石
        order.setType(1);
        order.setPrice(3);
        order.setNum(1);
        order.setCreate_time(new Date());
        order.setTo_value("");
        order.setStatus(4);//已完成
        orderService.insert(order);
        assert (order.getId()>0);
    }
    @Test
    public void search(){
        OrderList orderList=new OrderList();
        orderList.setUser_id(10012);
        orderList.setStatus(4);
        orderList.setStart(0);
        orderList.setPageNum(5);
        orderList.setCount(orderService.searchCount(orderList));
        orderList.setList(orderService.search(orderList));
        assert (orderList.getList().size()==5);

    }
    @Test
    public void orderCount(){
        int count=orderService.orderCount(10012);
        assert (count>0);
    }
}
