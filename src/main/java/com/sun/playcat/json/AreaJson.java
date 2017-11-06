package com.sun.playcat.json;

import com.sun.playcat.dao.AreaDao;
import com.sun.playcat.domain.ActionType;
import com.sun.playcat.domain.AreaList;
import com.sun.playcat.domain.BaseRequest;
import com.sun.playcat.domain.BaseResult;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by sunlin on 2017/8/13.
 */
public class AreaJson extends BaseJson {
    private AreaDao areaService;
    public AreaJson(WebApplicationContext context) {
        super(context);
        areaService=(AreaDao) context.getBean("areaService");
    }
    public String search(BaseRequest baseRequest){

        AreaList objList=gson.fromJson(baseRequest.getData(),AreaList.class);

        objList.setCount(areaService.searchCount(objList.getParent_id()));
        objList.setList(areaService.search(
                objList.getParent_id(),
                objList.getStart(),
                objList.getPageNum()));

        BaseResult baseResult = MessageHelp.BuildBaseResult(0,
                "", ActionType.AREA_SEARCH, "获取成功", gson.toJson(objList));
        return gson.toJson(baseResult);
    }
}
