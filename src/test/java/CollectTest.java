import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.playcat.dao.CollectDao;
import com.sun.playcat.domain.ActionType;
import com.sun.playcat.domain.BaseResult;
import com.sun.playcat.domain.Collect;
import com.sun.playcat.domain.CollectList;
import com.sun.playcat.json.MessageHelp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by sunlin on 2017/7/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class CollectTest {
    @Resource
    private CollectDao collectService;

    @Test
    public void insert()
    {
        Collect collect=new Collect();
        collect.setUid(1);
        collect.setSid(1);
        collect.setType(1);
        collect.setStatus(1);
        collect.setCreate_time(new Date());
        collectService.insert(collect);
        assert (collect.getId()>0);
    }
    @Test
    public void del()
    {
        Collect collect=new Collect();
        collect.setUid(10012);
        collect.setSid(1);
        collectService.del(collect);
    }
    @Test
    public void get()
    {
        Collect collect=new Collect();
        collect.setUid(10012);
        collect.setSid(1);
        collect=collectService.get(collect.getId());
        assert(collect!=null);
    }
    @Test
    public void searchGame()
    {
        CollectList collectList=new CollectList();
        collectList.setUid(10012);
        collectList.setStatus(1);
        collectList.setType(1);
        collectList.setStart(0);
        collectList.setPageNum(10);
        collectList.setCount(collectService.searchCount(collectList));
        collectList.setList(collectService.searchGame(collectList));
        assert (collectList.getCount()>0);
    }
    @Test
    public void getJson()
    {
        Gson gson= new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        Collect collect=new Collect();
        collect.setUid(10012);
        collect.setSid(1);
        collect=collectService.get(collect.getId());
        BaseResult baseResult;
        baseResult = MessageHelp.BuildBaseResult(0,
                "", ActionType.COLLECT_GET, "获取成功", gson.toJson(collect));
        String jsonStr=gson.toJson(baseResult);
        assert (jsonStr!="");
    }
}
