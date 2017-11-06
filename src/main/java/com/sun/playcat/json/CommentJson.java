package com.sun.playcat.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.playcat.common.Log;
import com.sun.playcat.dao.CommentDao;
import com.sun.playcat.domain.*;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by sunlin on 2017/7/25.
 */
public class CommentJson {
    public Gson gson;
    private WebApplicationContext webApplicationContext;
    public CommentJson(WebApplicationContext context){
        gson=new GsonBuilder().create();
        webApplicationContext=context;
    }
    public String add(BaseRequest baseRequest){
        Comment comment=gson.fromJson(baseRequest.getData(),Comment.class);
        CommentDao gameService=(CommentDao) webApplicationContext.getBean("commentService");
        gameService.insert(comment);

        return gson.toJson(MessageHelp.BuildBaseResult(0,
                "",ActionType.COMMENT_ADD,"添加成功"));

    }
    public String search(BaseRequest baseRequest){
        try {
            CommentList list=gson.fromJson(baseRequest.getData(),CommentList.class);
            CommentDao gameService=(CommentDao) webApplicationContext.getBean("commentService");
            //查询总数
            list.setCount(gameService.searchCount(list.getSid(),
                    list.getUserId(),list.getType(),list.getStatus()));
            //查询数据
            list.setComments(gameService.search(
                    list.getSid(),list.getUserId(),list.getType(),
                    list.getStatus(),list.getStart(),list.getPageNum()));
            BaseResult result=new BaseResult();
            result.setErrcode(0);
            result.setErrmsg("");
            result.setType(ActionType.COMMENT_SEARCH);
            result.setText("获取数据成功");
            list.setResult(result);
            return gson.toJson(list);

        }catch (Exception e){
            Log.error(e.toString());
            CommentList list=new CommentList();
            BaseResult result=new BaseResult();
            result.setErrcode(1);
            result.setErrmsg("");
            result.setType(ActionType.COMMENT_SEARCH);
            result.setText("服务错误");
            list.setResult(result);
            return gson.toJson(list);
        }
    }
}
