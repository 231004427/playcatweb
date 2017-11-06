package com.sun.playcat.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.playcat.dao.GamePlayDao;
import com.sun.playcat.domain.ActionType;
import com.sun.playcat.domain.BaseRequest;
import com.sun.playcat.domain.BaseResult;
import com.sun.playcat.domain.GamePlayList;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by sunlin on 2017/7/29.
 */
public class GamePlayJson extends BaseJson {
    private GamePlayDao gamePlayService;
    public GamePlayJson(WebApplicationContext context) {
        super(context);
        gamePlayService=(GamePlayDao) context.getBean("gamePlayService");
    }
    public String getTopList(BaseRequest baseRequest){
        GamePlayList dataList=gson.fromJson(baseRequest.getData(),GamePlayList.class);
        dataList.setGamePlays(gamePlayService.getTopList(dataList.getStart(),dataList.getPageNum(),dataList.getId()));
        BaseResult result=new BaseResult();
        result.setErrcode(0);
        result.setErrmsg("");
        result.setType(ActionType.GAME_PLAY_SEARCH);
        result.setText("获取数据成功");
        result.setData(gson.toJson(dataList));
        return gson.toJson(result);
    }
    public String getUserList(BaseRequest baseRequest){
        GamePlayList dataList=gson.fromJson(baseRequest.getData(),GamePlayList.class);
        dataList.setGamePlays(gamePlayService.getUserList(dataList.getStart(),dataList.getPageNum(),dataList.getId()));
        BaseResult result=new BaseResult();
        result.setErrcode(0);
        result.setErrmsg("");
        result.setType(ActionType.GAME_PLAY_SEARCH_USER);
        result.setText("获取数据成功");
        result.setData(gson.toJson(dataList));
        return gson.toJson(result);
    }
}
