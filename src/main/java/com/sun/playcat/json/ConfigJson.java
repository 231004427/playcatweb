package com.sun.playcat.json;

import com.sun.playcat.dao.ConfigDao;
import com.sun.playcat.domain.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * Created by sunlin on 2017/9/13.
 */
public class ConfigJson extends BaseJson {
    private ConfigDao configDao;
    public ConfigJson(WebApplicationContext context) {
        super(context);
        configDao=(ConfigDao) context.getBean("configService");
    }
    public String get(BaseRequest baseRequest){

        ConfigList obj=gson.fromJson(baseRequest.getData(),ConfigList.class);
        obj.setList(configDao.get(obj));
        BaseResult baseResult = MessageHelp.BuildBaseResult(0,
                "", ActionType.CONFIG_GET, "获取成功", gson.toJson(obj));
        return gson.toJson(baseResult);
    }
}
