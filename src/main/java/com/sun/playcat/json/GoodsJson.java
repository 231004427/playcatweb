package com.sun.playcat.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.playcat.common.Log;
import com.sun.playcat.dao.GoodsDao;
import com.sun.playcat.domain.ActionType;
import com.sun.playcat.domain.BaseRequest;
import com.sun.playcat.domain.BaseResult;
import com.sun.playcat.domain.GoodsList;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by sunlin on 2017/8/6.
 */
public class GoodsJson {
    public Gson gson;
    private WebApplicationContext webApplicationContext;
    public GoodsJson(WebApplicationContext context){
        gson=new GsonBuilder().create();
        webApplicationContext=context;
    }
    public String search(BaseRequest baseRequest){
            GoodsList listData=gson.fromJson(baseRequest.getData(),GoodsList.class);
            GoodsDao service=(GoodsDao) webApplicationContext.getBean("goodsService");
            //查询总数
            listData.setCount(service.searchCount(listData.getType()));
            //查询数据
            listData.setGoodsList(service.search(listData.getStart(),listData.getPageNum(),listData.getType()));

            BaseResult result=new BaseResult();
            result.setErrcode(0);
            result.setErrmsg("");
            result.setType(ActionType.GOODS_SEARCH);
            result.setText("获取数据成功");
            result.setData(gson.toJson(listData));

            return gson.toJson(result);
    }
}
