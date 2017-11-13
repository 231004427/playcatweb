package com.sun.playcat.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.playcat.common.GsonHelp;
import com.sun.playcat.dao.CollectDao;
import com.sun.playcat.domain.*;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by sunlin on 2017/7/27.
 */
public class CollectJson {

    public Gson gson;
    private WebApplicationContext webApplicationContext;
    private CollectDao collectService;

    public CollectJson(WebApplicationContext context){
        gson= GsonHelp.getGsonObj();
        webApplicationContext=context;
        collectService=(CollectDao) webApplicationContext.getBean("collectService");
    }
    public String del(BaseRequest baseRequest){
        Collect collect=gson.fromJson(baseRequest.getData(),Collect.class);
        collectService.del(collect);
        return gson.toJson(MessageHelp.BuildBaseResult(0,
                "", ActionType.COLLECT_DEL,"删除成功"));
    }
    public String insert(BaseRequest baseRequest){
        Collect collect=gson.fromJson(baseRequest.getData(),Collect.class);
        collectService.insert(collect);
        return gson.toJson(MessageHelp.BuildBaseResult(0,
                "", ActionType.COLLECT_ADD,"添加成功"));
    }

    public String get(BaseRequest baseRequest){
        Collect collect=gson.fromJson(baseRequest.getData(),Collect.class);
        Collect objData=collectService.get(collect.getId());
        BaseResult baseResult;
        baseResult = MessageHelp.BuildBaseResult(0,
                    "", ActionType.COLLECT_GET, "获取成功", gson.toJson(objData));
        return gson.toJson(baseResult);
    }
    public String searchGame(BaseRequest baseRequest){
        CollectList collectList=gson.fromJson(baseRequest.getData(),CollectList.class);
        if(collectList.getCount()<=0) {
            collectList.setCount(collectService.searchCount(collectList));
        }
        collectList.setList(collectService.searchGame(collectList));
        BaseResult baseResult;
        baseResult = MessageHelp.BuildBaseResult(0,
                "", ActionType.COLLECT_SARCH_GAME, "获取成功", gson.toJson(collectList));
        return gson.toJson(baseResult);
    }
}
