import com.sun.playcat.dao.AreaDao;
import com.sun.playcat.domain.AreaList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by sunlin on 2017/8/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class AreaTest {
    @Resource
    private AreaDao areaService;
    @Test
    public void search(){
        AreaList areaList=new AreaList();
        areaList.setCount(areaService.searchCount(86));
        areaList.setList(areaService.search(86,0,10));
        assert (areaList.getCount()>0);
    }
}
