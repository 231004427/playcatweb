import com.google.gson.Gson;
import com.sun.playcat.dao.GameDao;
import com.sun.playcat.domain.Game;
import com.sun.playcat.domain.GameList;
import com.sun.playcat.domain.ActionType;
import com.sun.playcat.domain.BaseResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by sunlin on 2017/7/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class GameTest {

    @Resource
    private GameDao gameService;
    @Test
    public void getRule()
    {
        Game game=gameService.getRule(1);

        assert (!game.getRule().isEmpty());

    }
    @Test
    public void getGame()
    {
        Game game=new Game();

        game=gameService.getGame(1);

        Gson gson=new Gson();
        String  gsonStr=gson.toJson(game);
        assert(!gsonStr.isEmpty());
    }

    @Test
    public void Search()
    {
        Gson gson=new Gson();
        GameList gameList=new GameList();
        gameList.setIsGood(0);
        gameList.setType(0);
        gameList.setStart(0);
        gameList.setPageNum(10);

        if(gameList.getType()==0)
        {
            gameList.setIsGood(1);
        }
        gameList.setCount(gameService.searchCount(gameList.getIsGood(),gameList.getType()));
        gameList.setGames(gameService.search(gameList.getStart(),
                gameList.getPageNum(),gameList.getIsGood(),gameList.getType()));

        BaseResult result=new BaseResult();
        result.setErrcode(0);
        result.setErrmsg("");
        result.setType(ActionType.GAME_SEARCH);
        result.setText("获取数据成功");
        result.setData(gson.toJson(gameList));
       String  gsonStr=gson.toJson(result);
        assert(!gsonStr.isEmpty());
    }
}
