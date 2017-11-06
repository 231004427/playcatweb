package com.sun.playcat.json;

import com.sun.playcat.dao.FriendDao;
import com.sun.playcat.dao.MessageDao;
import com.sun.playcat.domain.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by sunlin on 2017/8/26.
 */
public class MessageJson extends BaseJson {
    private MessageDao messageService;
    private FriendDao friendService;
    public MessageJson(WebApplicationContext context) {
        super(context);
        messageService =(MessageDao) context.getBean("messageService");
        friendService =(FriendDao) context.getBean("friendService");
    }
    public String add(BaseRequest baseRequest){
        Message message=gson.fromJson(baseRequest.getData(),Message.class);
        messageService.insert(message);
        return gson.toJson(MessageHelp.BuildBaseResult(0,
                "",ActionType.MESSAGE_ADD,"",gson.toJson(message)));

    }
    public String updateStatus(BaseRequest baseRequest){
        Message message=gson.fromJson(baseRequest.getData(),Message.class);
        messageService.updateStatus(message);
        return gson.toJson(MessageHelp.BuildBaseResult(0,
                "",ActionType.MESSAGE_UPDATE_STATUS,"",gson.toJson(message)));

    }
    public String updateStatusList(BaseRequest baseRequest){
        MessageList messageList=gson.fromJson(baseRequest.getData(),MessageList.class);
        messageService.updateStatusList(messageList.getList());
        return gson.toJson(MessageHelp.BuildBaseResult(0,
                "",ActionType.MESSAGE_UPDATE_LIST_STATUS,"更新成功"));

    }
    public String search(BaseRequest baseRequest){
        MessageList listData=gson.fromJson(baseRequest.getData(),MessageList.class);
        //查询总数
        listData.setCount(messageService.searchCount(listData));
        /*
        int start=listData.getCount()-listData.getStart()-listData.getPageNum();
        if(start<0){
            start=0;
        }
        listData.setStart(start);*/
        //查询数据
        listData.setList(messageService.search(listData));

        BaseResult result=new BaseResult();
        result.setErrcode(0);
        result.setErrmsg("");
        result.setType(ActionType.MESSAGE_SEARCH);
        result.setText("获取数据成功");
        result.setData(gson.toJson(listData));
        return gson.toJson(result);
    }
    public String addFriend(BaseRequest baseRequest){
        Message message=gson.fromJson(baseRequest.getData(),Message.class);
        //是否重复添加
        Friend friend=new Friend();
        friend.setUser_id(Integer.parseInt(message.getData()));
        friend.setFriend_id(message.getTo_user());
        int count= friendService.insertCount(friend);
        BaseResult baseResult;
        if(count>0)
        {
            baseResult=MessageHelp.BuildBaseResult(1,
                    "重复添加", ActionType.MESSAGE_ADD_FRIEND,"");
        }else{
            //消息是否重复（5分钟内不能重复申请）
            Date nowTime = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(nowTime);
            calendar.add(calendar.SECOND,-300);
            MessageList messageList=new MessageList();
            messageList.setFrom_user(1);
            messageList.setType(MessageType.ADD_FRIEND);//系统消息
            messageList.setTo_user(friend.getFriend_id());
            messageList.setCreate_time(new Date());
            int num=messageService.searchCount(messageList);
            if(num==0) {
                //添加新消息
                messageService.insert(message);
                baseResult=MessageHelp.BuildBaseResult(0,
                        "",ActionType.MESSAGE_ADD_FRIEND,"申请成功,等待通过");
            }else{
                baseResult=MessageHelp.BuildBaseResult(0,
                        "",ActionType.MESSAGE_ADD_FRIEND,"您已申请,请耐心等待");
            }

        }
        return gson.toJson(baseResult);
    }
}
