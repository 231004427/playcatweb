import com.sun.playcat.dao.ConfigDao;
import com.sun.playcat.domain.Config;
import com.sun.playcat.domain.ConfigList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sunlin on 2017/9/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class ConfigTest {
    @Resource
    private ConfigDao configService;
    @Test
    public void get(){
        ConfigList config=new ConfigList();
        config.setType(1);
        List<Config> list=configService.get(config);
        assert (list.size()>0);
    }
}
