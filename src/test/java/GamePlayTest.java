import com.sun.playcat.dao.GamePlayDao;
import com.sun.playcat.domain.Game;
import com.sun.playcat.domain.GamePlay;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by sunlin on 2017/7/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class GamePlayTest {
    @Resource
    private GamePlayDao gamePlayDao;
    @Test
    public void insert()
    {
        GamePlay gamePlay=new GamePlay();
        gamePlay.setUser_id(1);
        gamePlay.setGame_id(1);
        gamePlay.setPoints(0);
        gamePlay.setOnline(0);
        gamePlay.setLevel(0);
        gamePlay.setType(1);
        gamePlay.setCreate_time(new Date());
        gamePlay.setLast_time(new Date());
        gamePlay.setStatus(1);

        gamePlayDao.insert(gamePlay);

        assert (gamePlay.getId()>0);
    }
    @Test
    public void getTopList()
    {
        List<GamePlay> gamePlays=gamePlayDao.getTopList(0,1,1);
        assert (gamePlays!=null);
    }
    @Test
    public void getTopListCount()
    {
        int count=gamePlayDao.getTopListCount(1);
        assert (count>0);
    }
    @Test
    public void getUserList()
    {
        List<GamePlay> gamePlays=gamePlayDao.getUserList(0,1,1);
        assert (gamePlays!=null);
    }
    @Test
    public void getTopUserCount()
    {
        int count=gamePlayDao.getUserListCount(1);
        assert (count>0);
    }
}
