package com.sun.playcat.json;

import com.sun.playcat.dao.FriendDao;
import com.sun.playcat.dao.MessageDao;
import com.sun.playcat.domain.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by sunlin on 2017/8/19.
 */
public class FriendJson extends BaseJson {

    private FriendDao friendService;
    public FriendJson(WebApplicationContext context) {
        super(context);
        friendService =(FriendDao) context.getBean("friendService");
    }
    public String noReadCount(BaseRequest baseRequest){
        Friend friend=gson.fromJson(baseRequest.getData(),Friend.class);

        friend.setNo_read(friendService.noReadCount(friend.getUser_id()));
        BaseResult baseResult;
        baseResult=MessageHelp.BuildBaseResult(0,
                "",ActionType.FRIEND_NO_READ,"获取成功",gson.toJson(friend));
        return gson.toJson(baseResult);
    }
    public String setAllRead(BaseRequest baseRequest){
        Friend friend=gson.fromJson(baseRequest.getData(),Friend.class);
        friendService.setAllRead(friend);
        return gson.toJson(MessageHelp.BuildBaseResult(0,
                "",ActionType.FRIEND_SET_ALL_READ,"操作成功"));
    }
    public String insert(BaseRequest baseRequest){
        Friend friend=gson.fromJson(baseRequest.getData(),Friend.class);

        //是否重复添加
        int count= friendService.insertCount(friend);
        BaseResult baseResult;
        if(count>0)
        {
            baseResult=MessageHelp.BuildBaseResult(1,
                    "添加失败，重复添加", ActionType.FRIEND_ADD,"");
        }else{
            friendService.insert(friend);
            baseResult=MessageHelp.BuildBaseResult(0,
                    "", ActionType.FRIEND_ADD,"添加成功");
        }

        return gson.toJson(baseResult);
    }
    public String delete(BaseRequest baseRequest){
        Friend friend=gson.fromJson(baseRequest.getData(),Friend.class);
        //是否重复添加
        friendService.delete(friend);
        BaseResult baseResult;
            baseResult=MessageHelp.BuildBaseResult(0,
                    "", ActionType.FRIEND_DEL,"取消成功");

        return gson.toJson(baseResult);
    }

    public String messageList(BaseRequest baseRequest){
        FriendList listData=gson.fromJson(baseRequest.getData(),FriendList.class);
        listData.setCount(friendService.messageListCount(listData));
        listData.setList(friendService.messageList(listData));
        BaseResult baseResult=MessageHelp.BuildBaseResult(0,
                "",ActionType.FRIEND_MESSAGE,"获取数据成功",gson.toJson(listData));
        return gson.toJson(baseResult);

    }
    public String search(BaseRequest baseRequest){
        FriendList listData=gson.fromJson(baseRequest.getData(),FriendList.class);
        //查询总数
        listData.setCount(friendService.searchCount(listData));
        //查询数据
        listData.setList(friendService.search(listData));

        BaseResult result=new BaseResult();
        result.setErrcode(0);
        result.setErrmsg("");
        result.setType(ActionType.FRIEND_SEARCH);
        result.setText("获取数据成功");
        result.setData(gson.toJson(listData));
        return gson.toJson(result);
    }
}
