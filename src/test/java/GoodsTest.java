import com.sun.playcat.dao.GoodsDao;
import com.sun.playcat.domain.Goods;
import com.sun.playcat.domain.GoodsList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sunlin on 2017/8/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class GoodsTest {
    @Resource
    private GoodsDao goodsService;

    @Test
    public void searchCount()
    {
        int result=goodsService.searchCount(1);
        assert (result>0);
    }
    @Test
    public void search()
    {
        GoodsList goodsList=new GoodsList();
        goodsList.setType(1);
        goodsList.setStart(0);
        goodsList.setPageNum(5);

        List<Goods> list=goodsService.search(0,4,3);
        goodsList.setGoodsList(list);

        assert (list.size()>0);
    }
}
