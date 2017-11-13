package com.sun.playcat.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.playcat.common.GsonHelp;
import com.sun.playcat.dao.CollectDao;
import com.sun.playcat.dao.GameDao;
import com.sun.playcat.domain.*;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by sunlin on 2017/7/16.
 */
public class GameJson {
    public Gson gson;
    private WebApplicationContext webApplicationContext;
    public GameJson(WebApplicationContext context){
        gson= GsonHelp.getGsonObj();
        webApplicationContext=context;
    }
    public String search(BaseRequest baseRequest){

        GameList gameList=gson.fromJson(baseRequest.getData(),GameList.class);
        GameDao gameService=(GameDao) webApplicationContext.getBean("gameService");

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

        return gson.toJson(result);
    }
    public String getRule(BaseRequest baseRequest){
        Game game=gson.fromJson(baseRequest.getData(),Game.class);
        GameDao gameService=(GameDao) webApplicationContext.getBean("gameService");
        game=gameService.getRule(game.getId());

        BaseResult result=new BaseResult();
        if(game==null) {
            result.setErrcode(1);
            result.setErrmsg("对象不存在");
            result.setType(ActionType.GAME_GET);
            result.setText("");
        }else{
            result.setErrcode(0);
            result.setErrmsg("");
            result.setType(ActionType.GAME_GET);
            result.setText("获取数据成功");
        }
        result.setData(gson.toJson(game));
        return gson.toJson(result);
    }
    public String get(BaseRequest baseRequest){

        Game game=gson.fromJson(baseRequest.getData(),Game.class);

        GameDao gameService=(GameDao) webApplicationContext.getBean("gameService");
        game=gameService.getGame(game.getId());

        BaseResult result=new BaseResult();
        if(game==null) {
            result.setErrcode(1);
            result.setErrmsg("对象不存在");
            result.setType(ActionType.GAME_GET);
            result.setText("");
        }else{
            //是否收藏
            CollectDao collectService=(CollectDao) webApplicationContext.getBean("collectService");
            CollectList collect =new CollectList();
            collect.setUid(baseRequest.getUserid());
            collect.setSid(game.getId());
            collect.setType(1);
            collect.setStatus(1);
            int count=collectService.searchCount(collect);
            game.setCollect((count>0)?1:0);
            result.setErrcode(0);
            result.setErrmsg("");
            result.setType(ActionType.GAME_GET);
            result.setText("获取数据成功");
        }
        result.setData(gson.toJson(game));
        return gson.toJson(result);
    }
}
