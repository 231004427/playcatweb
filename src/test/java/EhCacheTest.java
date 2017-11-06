import com.sun.playcat.dao.EhCacheTestDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by sunlin on 2017/10/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class EhCacheTest {
    @Resource
    private EhCacheTestDao ehCacheTestService;
    @Test
    public void getTimestampTest() throws InterruptedException{
        System.out.println("第一次调用：" + ehCacheTestService.getTimestamp("param"));
        ehCacheTestService.insertTimestamp("param");
        Thread.sleep(2000);
        System.out.println("2秒之后调用：" + ehCacheTestService.getTimestamp("param"));
        Thread.sleep(11000);
        System.out.println("再过11秒之后调用：" + ehCacheTestService.getTimestamp("param"));
    }
}
