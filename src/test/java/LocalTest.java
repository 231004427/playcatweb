import com.sun.playcat.dao.LocalDao;
import com.sun.playcat.domain.Local;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by sunlin on 2017/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class LocalTest extends AbstractJUnit4SpringContextTests {
    //注入类
    @Resource
    private LocalDao localService;
    @Test
    public void insertLocal(){
        Local local=new Local();
        local.setUid(1);
        local.setCountry("中国");
        local.setProvince("浙江省");
        local.setCity("杭州");
        local.setPolitical("江干区");
        local.setRoute("九堡镇");
        local.setStreet("九盛路");
        local.setUpdateTime(new Date());
        local.setLatitude(0.233453);
        local.setLongitude(0.433231);
        local.setStatus(1);

        localService.insertLocal(local);

    }
}
